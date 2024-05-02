package com.example.bookingapp.controller;

import com.example.bookingapp.model.Host;
import com.example.bookingapp.model.dto.HostDto;
import com.example.bookingapp.service.CountryService;
import com.example.bookingapp.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/host")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> listAll(){
        return hostService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById (@PathVariable Long id){
        Host host = hostService.findById(id);

        if(host == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(host);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Host> create(@RequestBody HostDto hostDto){
        Host host = hostService.create(hostDto);

        if(host == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(host);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Host> edit(@PathVariable Long id, @RequestBody HostDto hostDto){
        Host host = hostService.update(id, hostDto);

        if(host == null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(host);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Host> delete(@PathVariable Long id){
        hostService.deleteById(id);

        if(hostService.findById(id)==null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
