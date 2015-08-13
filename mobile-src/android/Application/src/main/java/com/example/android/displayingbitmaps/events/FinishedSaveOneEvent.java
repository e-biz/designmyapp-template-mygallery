package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 22/04/15.
 */
public class FinishedSaveOneEvent {
    private String message;
    private String url;

    public FinishedSaveOneEvent(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
