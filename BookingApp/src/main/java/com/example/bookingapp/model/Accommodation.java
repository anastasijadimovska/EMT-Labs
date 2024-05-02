package com.example.bookingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    AccomodationCategory category;

    @ManyToOne
    Host host;

    Integer numRooms;

    public Accommodation() {

    }

    public Accommodation(String name, AccomodationCategory category, Host host, Integer numRooms){
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
