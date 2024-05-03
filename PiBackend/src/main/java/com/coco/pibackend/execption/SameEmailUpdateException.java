package com.coco.pibackend.execption;

public class SameEmailUpdateException extends RuntimeException {
    public SameEmailUpdateException() {
    }

    public SameEmailUpdateException(String message) {
        super(message);
    }
}
