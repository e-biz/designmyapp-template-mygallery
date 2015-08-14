package com.example.android.displayingbitmaps.service;

import com.example.android.displayingbitmaps.events.CreateFileEvent;
import com.example.android.displayingbitmaps.events.FinishedCreateFileEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 23/04/15.
 */
public enum CreateFileService {
    INSTANCE;

    public void onEventBackgroundThread(CreateFileEvent event) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        try {
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    event.getDir()      /* directory */
            );
            EventBus.getDefault().post(new FinishedCreateFileEvent(true, image));
        } catch (IOException e) {
            EventBus.getDefault().post(new FinishedCreateFileEvent(false, null));
        }
    }
}