package com.ideas2it.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * This class handles all the Exceptions when occurred.
 * like NoSuchElementException and custom Exception (EmployeeException)
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>
     * This method handle the EmployeeException while Exception Occurred
     * </p>
     * @param employeeException {@link EmployeeException} - Employee Exception Object
     * @return Response Entity as String (Error Message, HTTPStatus Code)
     */
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException) {
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * <p>
     * This method handles NoSuchElementException while Exception Occurred
     * </p>
     * @param noSuchElementException - NoSuchElementException Object
     * @return Response Entity as String (Error Message, HttpStatus Code)
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
