package com.econrich.homework.controller.advice;

import com.econrich.homework.enums.ErrorCode;
import com.econrich.homework.exception.InvalidPercentageException;
import com.econrich.homework.exception.NotFoundDepartmentException;
import com.econrich.homework.exception.NotFoundEmployeeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlers {
    private static final String VIEW_PAGE = "thymeleaf/error-page";
    private static final String MODEL_NAME = "errorMsg";

    @ExceptionHandler(NotFoundDepartmentException.class)
    public ModelAndView departmentHandle() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PAGE);
        modelAndView.addObject(MODEL_NAME, ErrorCode.NOT_FOUND_DEPARTMENT.getMsg());

        return modelAndView;
    }

    @ExceptionHandler(NotFoundEmployeeException.class)
    public ModelAndView employeeHandle() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PAGE);
        modelAndView.addObject(MODEL_NAME, ErrorCode.NOT_FOUND_EMPLOYEE.getMsg());

        return modelAndView;
    }

    @ExceptionHandler(InvalidPercentageException.class)
    public ModelAndView percentageHandle() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PAGE);
        modelAndView.addObject(MODEL_NAME, ErrorCode.INVALID_PERCENTAGE.getMsg());

        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView methodHandle() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PAGE);
        modelAndView.addObject(MODEL_NAME, ErrorCode.METHOD_EXCEPTION.getMsg());

        return modelAndView;
    }
}
