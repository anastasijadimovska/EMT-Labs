package com.example.bookingapp.data;

import com.example.bookingapp.model.Accommodation;
import com.example.bookingapp.model.AccomodationCategory;
import com.example.bookingapp.model.Country;
import com.example.bookingapp.model.Host;
import com.example.bookingapp.repository.AccommodationRepository;
import com.example.bookingapp.repository.CountryRepository;
import com.example.bookingapp.repository.HostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }


    @PostConstruct
    public void init(){
       List<Country> countries = new ArrayList<>();

        countries.add(new Country("Macedonia", "Europe"));
        countries.add(new Country("Brazil", "South America"));
        countries.add(new Country("Japan", "Asia"));
        countries.add(new Country("Australia", "Australia/Oceania"));
        countries.add(new Country("Egypt", "Africa"));
        countries.add(new Country("Canada", "North America"));
        countries.add(new Country("Italy", "Europe"));
        countries.add(new Country("Argentina", "South America"));
        countries.add(new Country("China", "Asia"));
        countries.add(new Country("New Zealand", "Australia/Oceania"));
        countries.add(new Country("Nigeria", "Africa"));
        countries.add(new Country("United States", "North America"));
        countries.add(new Country("Spain", "Europe"));
        countries.add(new Country("Peru", "South America"));
        countries.add(new Country("India", "Asia"));
        countries.add(new Country("Fiji", "Australia/Oceania"));
        countries.add(new Country("Kenya", "Africa"));
        countries.add(new Country("Mexico", "North America"));
        countries.add(new Country("France", "Europe"));
        countries.add(new Country("Chile", "South America"));

        countryRepository.saveAll(countries);

        String[] names = {
                "Anastasia", "John", "Emma", "Michael", "Sophia",
                "Liam", "Olivia", "William", "Isabella", "James",
                "Mia", "Benjamin", "Charlotte", "Lucas", "Amelia",
                "Alexander", "Harper", "Ethan", "Evelyn", "Daniel"
        };

        String[] surnames = {
                "Smith", "Johnson", "Williams", "Jones", "Brown",
                "Davis", "Miller", "Wilson", "Moore", "Taylor",
                "Anderson", "Thomas", "Jackson", "White", "Harris",
                "Martin", "Thompson", "Garcia", "Martinez", "Robinson"
        };
        List<Host> hosts = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String name = names[i % names.length];
            String surname = surnames[i % surnames.length];

            Host host = new Host(name, surname, countries.get(i));
            hosts.add(host);
        }
        hostRepository.saveAll(hosts);

        List<Accommodation> accommodations = new ArrayList<>();
        String[] accommodationNames = {
                "Cozy Room",
                "Beach House",
                "Modern Apartment",
                "Luxury Hotel",
                "Rustic Cottage",
                "City Loft",
                "Mountain Retreat",
                "Seaside Villa",
                "Charming B&B",
                "Ski Chalet",
                "Urban Studio",
                "Family Farmhouse",
                "Historic Inn",
                "Lakefront Cabin",
                "Tropical Resort",
                "Quaint Guesthouse",
                "Elegant Mansion",
                "Budget Hostel",
                "Desert Oasis",
                "Rural Lodge"
        };
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            String accommodationName = accommodationNames[i];
            Host host = hosts.get(i);
            int numberOfRooms = (i % 10) + 1;

            Accommodation accommodation = new Accommodation(accommodationName, AccomodationCategory.values()[random.nextInt(AccomodationCategory.values().length)], host, numberOfRooms);
            accommodations.add(accommodation);
        }

        accommodationRepository.saveAll(accommodations);
    }
}
