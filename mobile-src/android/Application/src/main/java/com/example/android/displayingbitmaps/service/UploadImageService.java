package com.example.android.displayingbitmaps.service;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.events.FinishedUploadFileEvent;
import com.example.android.displayingbitmaps.events.UploadImageEvent;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 23/04/15.
 */
public enum UploadImageService {
    INSTANCE;

    private static final String TAG = "UploadImageService";
    private static final String URI = "image/android";


    public void onEventBackgroundThread(UploadImageEvent event) {
        int code = 0;

        try {
            final byte[] data = FileUtils.readFileToByteArray(new File(event.getFilePath()));
            HttpURLConnection urlConnection;
            URL url = new URL(event.getBackendUrl() + URI);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(data.length);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(data);
            code = urlConnection.getResponseCode();
            //Handle one redirection from http to https or vice-versa
            switch (code) {
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    url = new URL(urlConnection.getHeaderField("Location"));
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setFixedLengthStreamingMode(data.length);
                    out = new BufferedOutputStream(urlConnection.getOutputStream());
                    out.write(data);
                    code = urlConnection.getResponseCode();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error while sending an image", e);
        }

        if (code == HttpURLConnection.HTTP_OK) {
            EventBus.getDefault().post(new FinishedUploadFileEvent(true));
            return;
        }
        EventBus.getDefault().post(new FinishedUploadFileEvent(false));
    }
}
