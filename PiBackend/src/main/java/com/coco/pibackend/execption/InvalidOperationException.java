package com.coco.pibackend.execption;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}
