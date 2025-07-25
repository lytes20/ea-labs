package edu.miu.Lab4PartA;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    private String flightNumber;

    @Column(name = "departure_from")
    private String from;

    @Column(name = "arrival_to")
    private String to;

    private LocalDate date;

    protected Flight() {}

    public Flight(String flightNumber, String from, String to, LocalDate date) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
