package com.example.android.displayingbitmaps.events;

/**
 * Created by excilys on 22/04/15.
 */
public class FinishedSaveEvent {
    private String message;

    public FinishedSaveEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
