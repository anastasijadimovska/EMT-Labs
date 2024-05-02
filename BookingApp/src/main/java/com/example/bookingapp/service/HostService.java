package com.example.bookingapp.service;

import com.example.bookingapp.model.Host;
import com.example.bookingapp.model.dto.HostDto;

import java.util.List;

public interface HostService {
    List<Host> listAll();
    void deleteById(Long id);
    Host findById(Long id);
    Host create(HostDto hostDto);
    Host update(Long id,HostDto hostDto);
}
