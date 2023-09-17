package ru.sber.beautifulcode.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.sber.beautifulcode.exception.ServiceException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handle(Exception exception) {
        if (exception instanceof ServiceException serviceException) {
            return new ResponseEntity<>(
                    new ErrorMessage(serviceException.getMessage()),
                    serviceException.getHttpStatus()
            );
        }

        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
