package com.example.android.displayingbitmaps.events;

import android.content.Context;

/**
 * Created by excilys on 22/04/15.
 */
public class SaveOneEvent {

    private String url;
    private Context context;

    public SaveOneEvent(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }
}
