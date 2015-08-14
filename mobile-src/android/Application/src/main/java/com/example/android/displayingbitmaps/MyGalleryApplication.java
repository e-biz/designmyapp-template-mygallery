package com.example.android.displayingbitmaps;

import android.app.Application;

import com.example.android.displayingbitmaps.service.CreateFileService;
import com.example.android.displayingbitmaps.service.DownloadListService;
import com.example.android.displayingbitmaps.service.SaveService;
import com.example.android.displayingbitmaps.service.UploadImageService;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 22/04/15.
 */
public class MyGalleryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus eventBus = EventBus.getDefault();
        eventBus.register(SaveService.INSTANCE);
        eventBus.register(DownloadListService.INSTANCE);
        eventBus.register(CreateFileService.INSTANCE);
        eventBus.register(UploadImageService.INSTANCE);

    }
}
