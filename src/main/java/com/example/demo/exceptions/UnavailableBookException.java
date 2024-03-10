package com.example.demo.exceptions;

public class UnavailableBookException extends RuntimeException {

    public UnavailableBookException(String msg) {
        super(msg);
    }
}
