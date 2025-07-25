package edu.miu.Lab4PartA;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "passenger_flights",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    @OrderColumn(name = "flight_order")
    private List<Flight> flights = new ArrayList<>();

    protected Passenger() {}

    public Passenger(String name) {
        this.name = name;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }


    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights=" +  +
                '}';
    }
}
