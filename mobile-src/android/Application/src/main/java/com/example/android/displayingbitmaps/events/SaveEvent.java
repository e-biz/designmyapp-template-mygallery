package com.example.android.displayingbitmaps.events;

import android.content.Context;

/**
 * Created by excilys on 22/04/15.
 */
public class SaveEvent {

    private String[] urls;
    private Context context;

    public SaveEvent(Context context, String... urls) {
        this.context = context;
        this.urls = urls;
    }

    public Context getContext() {
        return context;
    }

    public String[] getUrls() {
        return urls;
    }
}
