package com.hobbyproject.carrentalservice.exceptions;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CarNotFoundException</h2>
 * @date 2024-04-03
 */
public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(String message) {
        super(message);
    }
}
