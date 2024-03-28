package com.nagarro.java.mini.assignment2.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorMessage {
    private final String message;
    private final int code;
    private final String timestamp;

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
