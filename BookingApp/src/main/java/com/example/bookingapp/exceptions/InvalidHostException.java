package com.example.bookingapp.exceptions;

public class InvalidHostException extends RuntimeException{
    public InvalidHostException() {
        super("Invalid host!");
    }
}
