package com.example.android.displayingbitmaps.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by excilys on 23/04/15.
 */
public class ImagesRef {
    private Integer processed;
    private List<String> thumbnails;

    public ImagesRef() {
        this.thumbnails = new ArrayList<String>();
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
