package com.econrich.homework.exception;

public class NotFoundEmployeeException extends RuntimeException{
    public NotFoundEmployeeException(String msg){
        super(msg);
    }
}
