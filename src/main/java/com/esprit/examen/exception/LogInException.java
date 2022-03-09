package com.esprit.examen.exception;

public class LogInException extends Exception {

    public LogInException() {
    }

    public LogInException(String message) {
        super(message);
    }

    public LogInException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogInException(Throwable cause) {
        super(cause);
    }
}
