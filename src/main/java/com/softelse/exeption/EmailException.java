package com.softelse.exeption;

public class EmailException extends RuntimeException{

    public EmailException() {
    }

    public EmailException(String message) {
        super(message);
    }
}
