package com.hobbyproject.carrentalservice.exceptions.handler;

import com.hobbyproject.carrentalservice.exceptions.CarNotFoundException;
import com.hobbyproject.carrentalservice.exceptions.model.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>GlobalExceptionHandler</h2>
 * @date 2024-04-02
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getAllErrors();
        ErrorMessage message;

        if (!errors.isEmpty()) {
            message = new ErrorMessage(errors.get(0).getDefaultMessage());
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }

        message = new ErrorMessage("Bad Request");
        return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(Exception e) {
        ErrorMessage message = new ErrorMessage("Not Found");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCarNotFoundException(CarNotFoundException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorMessage> handleNumberFormatException(Exception e) {
        ErrorMessage message = new ErrorMessage("Enter a valid user Id");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> handleNotReadableException(Exception e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalException(IllegalArgumentException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraints(ConstraintViolationException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}
