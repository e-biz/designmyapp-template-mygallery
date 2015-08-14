package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 23/04/15.
 */
public class FinishedDownloadListEvent {

    private boolean success;

    public FinishedDownloadListEvent(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
