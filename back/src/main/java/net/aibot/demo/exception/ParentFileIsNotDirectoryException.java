package net.aibot.demo.exception;

import org.springframework.http.HttpStatus;

public class ParentFileIsNotDirectoryException extends Throwable {
    private final HttpStatus status;

    public ParentFileIsNotDirectoryException() {
        super();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ParentFileIsNotDirectoryException(String errorMsg) {
        super(errorMsg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ParentFileIsNotDirectoryException(String errorMsg, HttpStatus status) {
        super(errorMsg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
