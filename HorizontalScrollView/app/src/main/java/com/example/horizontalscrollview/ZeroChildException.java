package com.example.horizontalscrollview;

/**
 * Created by sudhanshu on 8/6/16.
 */
public class ZeroChildException extends Exception {

    private static final long serialVersionUID = 1L;

    public ZeroChildException() {

    }

    public ZeroChildException(String errorMessage) {
        super(errorMessage);
    }
}
