package net.aibot.demo.service;

import org.springframework.http.HttpStatus;

public class EmptyObjectException extends Throwable {
    private final HttpStatus status;

    public EmptyObjectException() {
        super();
        this.status = HttpStatus.OK;
    }

    public EmptyObjectException(String errorMsg) {
        super(errorMsg);
        this.status = HttpStatus.OK;
    }

    public EmptyObjectException(String errorMsg, HttpStatus status) {
        super(errorMsg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
