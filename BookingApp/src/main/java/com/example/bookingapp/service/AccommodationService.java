package com.example.bookingapp.service;

import com.example.bookingapp.model.Accommodation;
import com.example.bookingapp.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(AccommodationDto accommodationDto);
    Accommodation update(Long id, AccommodationDto accommodationDto);

    void deleteById(Long id);
    void rent(Long id);
    void onAccommodationRent();

}
