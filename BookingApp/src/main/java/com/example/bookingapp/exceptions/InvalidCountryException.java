package com.example.bookingapp.exceptions;

public class InvalidCountryException extends RuntimeException{
    public InvalidCountryException() {
        super("Invalid country!");
    }
}
