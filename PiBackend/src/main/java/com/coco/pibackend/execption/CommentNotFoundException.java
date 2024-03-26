package com.coco.pibackend.execption;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
