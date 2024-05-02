package com.example.bookingapp.listeners;

import com.example.bookingapp.model.events.AccommodationRentEvent;
import com.example.bookingapp.service.AccommodationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationRentListener {

    private final AccommodationService accommodationService;

    public AccommodationRentListener(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @EventListener
    public void onAccommodationRent (AccommodationRentEvent event){
        accommodationService.onAccommodationRent();
    }
}
