package com.coco.pibackend.execption;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException() {
    }

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
