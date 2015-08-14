package com.example.android.displayingbitmaps.events;

import java.io.File;

/**
 * Created by excilys on 23/04/15.
 */
public class CreateFileEvent {

    private File dir;

    public CreateFileEvent(File dir) {
        this.dir = dir;
    }

    public File getDir() {
        return dir;
    }
}
