package com.api.project.exception;

public class NotFoundDepartmentException extends RuntimeException{
    public NotFoundDepartmentException(String msg){
        super(msg);
    }
}
