package com.example.android.displayingbitmaps.events;

import java.io.File;

/**
 * Created by excilys on 23/04/15.
 */
public class FinishedCreateFileEvent {

    private boolean success;
    private File file;

    public FinishedCreateFileEvent(boolean success, File file) {
        this.success = success;
        this.file = file;
    }

    public boolean isSuccess() {
        return success;
    }

    public File getFile() {
        return file;
    }
}
