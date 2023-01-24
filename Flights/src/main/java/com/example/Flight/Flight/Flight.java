package com.example.Flight.Flight;

import com.example.Flight.Passengers.Passenger;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @SequenceGenerator(
            name = "flight_generator",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_generator"
    )
    private Long flightId;
    private String departFrom;
    private String destination;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime destinationTime;
    private Integer seats;

    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "flight_passenger_map",
            joinColumns = @JoinColumn(
                    name = "flight_id",
                    referencedColumnName = "flightId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "passenger_id",
                    referencedColumnName = "passengerId"
            )
    )
    private Set<Passenger> passengers = new HashSet<>();



    public Flight(String departFrom, String destination, LocalDateTime departTime, LocalDateTime destinationTime,
                  Integer seats, Set<Passenger> passengers) {
        this.departFrom = departFrom;
        this.destination = destination;
        this.departTime = departTime;
        this.destinationTime = destinationTime;
        this.seats = seats;
        this.passengers = passengers;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalDateTime departTime) {
        this.departTime = departTime;
    }

    public LocalDateTime getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(LocalDateTime destinationTime) {
        this.destinationTime = destinationTime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Flight flight))
            return false;
        return Objects.equals(this.flightId, flight.flightId) && Objects.equals(this.departFrom, flight.departFrom)
                && Objects.equals(this.destination, flight.destination)
                && Objects.equals(this.departTime, flight.departTime)
                && Objects.equals(this.destinationTime, flight.destinationTime)
                && Objects.equals(this.seats, flight.seats)
                && Objects.equals(this.passengers, flight.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.flightId, this.departFrom, this.destination, this.departTime,
                this.destinationTime, this.seats, this.passengers);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + flightId +
                ", departFrom='" + departFrom + '\'' +
                ", destination='" + destination + '\'' +
                ", departTime=" + departTime +
                ", destinationTime=" + destinationTime +
                ", seats=" + seats +
                ", passengers=" + passengers +
                '}';
    }
}
