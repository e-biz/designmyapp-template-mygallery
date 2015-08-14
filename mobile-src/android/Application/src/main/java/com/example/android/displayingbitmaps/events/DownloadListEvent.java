package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 23/04/15.
 */
public class DownloadListEvent {

    private String backendUrl;

    public DownloadListEvent(String backendUrl) {
        this.backendUrl = backendUrl;
    }

    public String getBackendUrl() {
        return backendUrl;
    }
}
