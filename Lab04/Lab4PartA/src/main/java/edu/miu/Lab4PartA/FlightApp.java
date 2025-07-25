package edu.miu.Lab4PartA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class FlightApp implements CommandLineRunner {

    @Autowired
    private PassengerRepository passengerRepo;

    public static void main(String[] args) {
        SpringApplication.run(FlightApp.class, args);
    }

    @Override
    public void run(String... args) {
        Flight f1 = new Flight("100", "Uganda", "US", LocalDate.of(2025, 8, 1));
        Flight f2 = new Flight("101", "Kampala", "Nairobi", LocalDate.of(2025, 8, 2));
        Flight f3 = new Flight("111", "Gwen", "France", LocalDate.of(2025, 8, 3));

        Passenger p = new Passenger("Gideon Bamuleseyo");
        p.addFlight(f1);
        p.addFlight(f2);
        p.addFlight(f3);

        passengerRepo.save(p);

        passengerRepo.findAll().forEach(System.out::println);
    }
}
