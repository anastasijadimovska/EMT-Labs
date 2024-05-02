package com.example.bookingapp.service;

import com.example.bookingapp.model.Country;
import com.example.bookingapp.model.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<Country> listAll();

    Country findById(Long id);

    void deleteById(Long id);

    Country create(CountryDto countryDto);

    Country update(Long id, CountryDto countryDto);
}
