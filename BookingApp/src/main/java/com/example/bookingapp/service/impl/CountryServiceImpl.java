package com.example.bookingapp.service.impl;

import com.example.bookingapp.exceptions.InvalidCountryException;
import com.example.bookingapp.model.Country;
import com.example.bookingapp.model.dto.CountryDto;
import com.example.bookingapp.repository.CountryRepository;
import com.example.bookingapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(InvalidCountryException::new);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country create(CountryDto countryDto) {
        return countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent()));
    }

    @Override
    public Country update(Long id, CountryDto countryDto) {
        Country country = this.findById(id);

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return countryRepository.save(country);
    }
}
