package com.example.bookingapp.controller;

import com.example.bookingapp.model.Accommodation;
import com.example.bookingapp.model.AccomodationCategory;
import com.example.bookingapp.model.dto.AccommodationDto;
import com.example.bookingapp.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll(){
        return accommodationService.listAll();
    }

    @GetMapping("/categories")
    public List<AccomodationCategory> getAccommodationCategories(){
        return List.of(AccomodationCategory.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id){
        Optional<Accommodation> accommodation = accommodationService.findById(id);

        if(accommodation.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
          return ResponseEntity.ok(accommodation.get());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> create (@RequestBody AccommodationDto accommodationDto){

        return accommodationService.create(accommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Accommodation> edit (@PathVariable Long id, @RequestBody AccommodationDto accommodationDto){
        Accommodation accommodation = accommodationService.update(id, accommodationDto);

        if(accommodation == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(accommodation);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Accommodation> delete(@PathVariable Long id){
        accommodationService.deleteById(id);

        if(accommodationService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rent(@PathVariable Long id){
        Optional<Accommodation> accommodation = accommodationService.findById(id);

        if(accommodation.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            accommodationService.rent(id);
            return ResponseEntity.ok(accommodation.get());
        }
    }
}
