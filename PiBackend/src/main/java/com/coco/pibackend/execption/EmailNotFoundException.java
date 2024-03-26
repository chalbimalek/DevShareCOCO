package com.coco.pibackend.execption;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
    }

    public EmailNotFoundException(String message) {
        super(message);
    }
}
