package com.example.bookingapp.exceptions;

public class InvalidAccommodationException extends RuntimeException{
    public InvalidAccommodationException() {
        super("Invalid Accommodation!");
    }

    public InvalidAccommodationException(String message) {
        super(message);
    }
}
