package me.roman.app.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception{
    public ServiceException(String message) {
        super(message);
    }
}
