package com.example.bookingapp.controller;

import com.example.bookingapp.model.Country;
import com.example.bookingapp.model.Host;
import com.example.bookingapp.model.dto.CountryDto;
import com.example.bookingapp.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> listAll(){
        return countryService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        Country country = countryService.findById(id);

        if(country == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Country> create(@RequestBody CountryDto countryDto){
        Country country = countryService.create(countryDto);

        if(country == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> edit (@PathVariable Long id, @RequestBody CountryDto countryDto){
        Country country = countryService.update(id, countryDto);

        if(country == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Host> delete(@PathVariable Long id){
        countryService.deleteById(id);

        if(countryService.findById(id)==null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
