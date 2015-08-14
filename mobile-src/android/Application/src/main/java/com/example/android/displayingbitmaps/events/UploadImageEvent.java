package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 23/04/15.
 */
public class UploadImageEvent {

    private String filePath;
    private String backendUrl;

    public UploadImageEvent(String filePath, String backendUrl) {
        this.filePath = filePath;
        this.backendUrl = backendUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBackendUrl() {
        return backendUrl;
    }
}
