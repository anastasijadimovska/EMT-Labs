package com.example.bookingapp.model.dto;

import com.example.bookingapp.model.AccomodationCategory;
import lombok.Data;

@Data
public class AccommodationDto {
    String name;
    AccomodationCategory category;
    Long host;
    Integer numRooms;
}
