package com.example.android.displayingbitmaps.service;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.events.DownloadListEvent;
import com.example.android.displayingbitmaps.events.FinishedDownloadListEvent;
import com.example.android.displayingbitmaps.model.ImagesRef;
import com.example.android.displayingbitmaps.provider.Images;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 23/04/15.
 */
public enum DownloadListService {
    INSTANCE;

    private static final String TAG = "DownloadListService";
    private static final String URI = "image/list";


    public void onEventBackgroundThread(DownloadListEvent event) {
        int code = 0;
        HttpURLConnection urlConnection;
        InputStream responseInputStream = null;
        try {
            URL url = new URL(event.getBackendUrl() + URI);
            urlConnection = (HttpURLConnection) url.openConnection();
            code = urlConnection.getResponseCode();
            //Handle one redirection from http to https or vice-versa
            switch (code) {
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    url = new URL(urlConnection.getHeaderField("Location"));
                    urlConnection = (HttpURLConnection) url.openConnection();
                    code = urlConnection.getResponseCode();
                    break;
                default:
                    break;
            }
            responseInputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            Log.e(TAG, "Error while getting HttpResponse");
        }
        if (code == HttpURLConnection.HTTP_OK && responseInputStream != null) {
            if (parseResponse(responseInputStream)) {
                EventBus.getDefault().post(new FinishedDownloadListEvent(true));
                return;
            }
        }
        EventBus.getDefault().post((new FinishedDownloadListEvent(false)));
    }


    private boolean parseResponse(InputStream inputStream) {
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line);
            }
            inputStream.close();
            String result = sBuilder.toString();

            ObjectMapper mapper = new ObjectMapper();
            ImagesRef ref = mapper.readValue(result, ImagesRef.class);
            Images.setUrls(ref.getThumbnails().toArray(new String[ref.getProcessed()]));
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error while parsing json response", e);
            return false;
        }
    }
}
