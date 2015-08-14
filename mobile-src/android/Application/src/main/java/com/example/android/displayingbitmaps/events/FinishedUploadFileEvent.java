package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 23/04/15.
 */
public class FinishedUploadFileEvent {

    private boolean success;

    public FinishedUploadFileEvent(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
