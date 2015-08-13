package com.example.android.displayingbitmaps.Service;

import android.content.Context;
import android.os.Environment;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.R;
import com.example.android.displayingbitmaps.events.FinishedSaveEvent;
import com.example.android.displayingbitmaps.events.FinishedSaveOneEvent;
import com.example.android.displayingbitmaps.events.SaveEvent;
import com.example.android.displayingbitmaps.events.SaveOneEvent;
import com.example.android.displayingbitmaps.util.ImageFetcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 22/04/15.
 */
public enum SaveService {
    INSTANCE;

    private static final String TAG = "SaveService";

    private boolean savingAll;
    private HashSet<String> urlMap;

    private SaveService() {
        savingAll = false;
        urlMap = new HashSet<>();
    }


    public void onEventBackgroundThread(SaveEvent event) {
        boolean success = true;
        savingAll = true;
        for (String imageUrl : event.getUrls()) {
            if (!save(imageUrl, event.getContext())) {
                success = false;
            }
        }
        savingAll = false;
        if (success) {
            EventBus.getDefault().post(new FinishedSaveEvent(event.getContext().getString(R.string.saved)));
        } else {
            EventBus.getDefault().post(new FinishedSaveEvent(event.getContext().getString(R.string.save_error)));
        }
    }

    public void onEventBackgroundThread(SaveOneEvent event) {
        urlMap.add(event.getUrl());
        boolean success = save(event.getUrl(), event.getContext());
        urlMap.remove(event.getUrl());
        if (success) {
            EventBus.getDefault().post(new FinishedSaveOneEvent(event.getContext().getString(R.string.saved), event.getUrl()));
        } else {
            EventBus.getDefault().post(new FinishedSaveOneEvent(event.getContext().getString(R.string.save_error), event.getUrl()));
        }
    }

    private boolean save(String imageUrl, Context context) {
        ImageFetcher imageFetcher = new ImageFetcher(context, 0);

        // Get the path to the Pictures directory
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // Use the last part of the mImageUrl path as the file name
        File file = new File(storageDir, getFileName(imageUrl));

        // If the file doesn't exist, create a new one and save the image inside
        // If a file already exist, doesn't do anything
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);

                if (context.getResources().getBoolean(R.bool.localImages)) {
                    imageFetcher.readAssetToStream(imageUrl, outputStream);
                } else {
                    imageFetcher.downloadUrlToStream(imageUrl, outputStream);
                }
            } catch (IOException e) {
                Log.e(TAG, "Error while saving " + imageUrl, e);
                return false;
            }
        }
        return true;
    }

    /**
     * Split the url on the "/" and return the last part
     * @param fileUrl to split
     * @return Last part of the url
     */
    private String getFileName(String fileUrl) {
        Pattern p = Pattern.compile("/");
        String[] parts = p.split(fileUrl);
        return parts[parts.length - 1];
    }

    public boolean isSavingAll() {
        return savingAll;
    }

    public boolean isInSaving(String url) {
        return urlMap.contains(url);
    }
}
