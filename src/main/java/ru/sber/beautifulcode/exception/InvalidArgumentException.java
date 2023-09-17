package ru.sber.beautifulcode.exception;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends ServiceException {

    public InvalidArgumentException(String errorMessage) {
        super(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
