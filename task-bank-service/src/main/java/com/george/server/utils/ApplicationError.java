package com.george.server.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationError {
    private int statusCode;
    private String message;

    public ApplicationError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
