package com.coco.pibackend.execption;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
    }

    public TagNotFoundException(String message) {
        super(message);
    }
}
