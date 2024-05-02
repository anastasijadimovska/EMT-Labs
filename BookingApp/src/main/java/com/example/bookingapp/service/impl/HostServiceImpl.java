package com.example.bookingapp.service.impl;

import com.example.bookingapp.exceptions.InvalidCountryException;
import com.example.bookingapp.exceptions.InvalidHostException;
import com.example.bookingapp.model.Country;
import com.example.bookingapp.model.Host;
import com.example.bookingapp.model.dto.HostDto;
import com.example.bookingapp.repository.CountryRepository;
import com.example.bookingapp.repository.HostRepository;
import com.example.bookingapp.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public Host findById(Long id) {
        return hostRepository.findById(id).orElseThrow(InvalidHostException::new);
    }

    @Override
    public Host create(HostDto hostDto) {
        Country country = countryRepository.findById(hostDto.getCountry()).orElseThrow(InvalidCountryException::new);
        return hostRepository.save(new Host(hostDto.getName(), hostDto.getSurname(), country));
    }

    @Override
    public Host update(Long id, HostDto hostDto) {
        Country country = countryRepository.findById(hostDto.getCountry()).orElseThrow(InvalidCountryException::new);
        Host host = this.findById(id);

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country);

        return hostRepository.save(host);
    }
}
