package com.coco.pibackend.execption;

public class EmptyPostException extends RuntimeException {
    public EmptyPostException() {
    }

    public EmptyPostException(String message) {
        super(message);
    }
}
