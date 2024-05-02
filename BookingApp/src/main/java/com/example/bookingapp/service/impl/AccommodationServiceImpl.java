package com.example.bookingapp.service.impl;

import com.example.bookingapp.exceptions.InvalidHostException;
import com.example.bookingapp.model.Accommodation;
import com.example.bookingapp.model.Host;
import com.example.bookingapp.model.dto.AccommodationDto;
import com.example.bookingapp.model.events.AccommodationRentEvent;
import com.example.bookingapp.repository.AccommodationRepository;
import com.example.bookingapp.repository.HostRepository;
import com.example.bookingapp.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    private final ApplicationEventPublisher applicationEventPublisher;


    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> create(AccommodationDto accommodationDto) {
        Optional<Host> host = hostRepository.findById(accommodationDto.getHost());
        if(host.isPresent()) {
            Accommodation accommodation = new Accommodation(accommodationDto.getName(), accommodationDto.getCategory(), host.get(), accommodationDto.getNumRooms());
            accommodationRepository.save(accommodation);
            return Optional.of(accommodation);

        } else throw new InvalidHostException();

    }

    @Override
    public Accommodation update(Long id, AccommodationDto accommodationDto) {
        Host host = hostRepository.findById(accommodationDto.getHost()).orElseThrow(InvalidHostException::new);
        Accommodation accommodation = this.findById(id).get();

        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());

        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public void rent(Long id) {
        Accommodation accommodation = this.findById(id).get();
        Integer numRooms = accommodation.getNumRooms();

        if(numRooms == 0){
         applicationEventPublisher.publishEvent(new AccommodationRentEvent(accommodation));
        } else {
            numRooms -= 1;
        }

        accommodation.setNumRooms(numRooms);


        accommodationRepository.save(accommodation);
    }

    @Override
    public void onAccommodationRent(){
        System.out.println("You cannot rent this accommodation because it doesn't have any free rooms!");
    }
}
