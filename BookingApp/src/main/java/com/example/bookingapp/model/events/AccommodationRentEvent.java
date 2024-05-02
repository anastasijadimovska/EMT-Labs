package com.example.bookingapp.model.events;

import com.example.bookingapp.model.Accommodation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccommodationRentEvent extends ApplicationEvent {
    public AccommodationRentEvent(Accommodation accommodation){
        super(accommodation);
    }
}
