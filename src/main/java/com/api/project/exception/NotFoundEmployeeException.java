package com.api.project.exception;

public class NotFoundEmployeeException extends RuntimeException{
    public NotFoundEmployeeException(String msg){
        super(msg);
    }
}
