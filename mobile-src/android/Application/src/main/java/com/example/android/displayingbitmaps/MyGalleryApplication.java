package com.example.android.displayingbitmaps;

import android.app.Application;

import com.example.android.displayingbitmaps.Service.SaveService;

import de.greenrobot.event.EventBus;

/**
 * Created by excilys on 22/04/15.
 */
public class MyGalleryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(SaveService.INSTANCE);
    }
}
