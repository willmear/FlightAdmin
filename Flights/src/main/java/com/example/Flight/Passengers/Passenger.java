package com.example.Flight.Passengers;

import com.example.Flight.Flight.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @SequenceGenerator(
            name = "passenger_generator",
            sequenceName = "passenger_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_generator"
    )
    private Long passengerId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String seatOut;
    private String seatIn;
    private String boardingNumOut;
    private String boardingNumIn;
    @Enumerated(EnumType.STRING)
    private TravelReason travelReason;

    @JsonIgnore
    @ManyToMany(mappedBy = "passengers")
    private Set<Flight> flights = new HashSet<>();

    public Passenger(String firstName, String lastName, String email, LocalDate dob, String seatOut, String seatIn,
                     String boardingNumOut, String boardingNumIn, TravelReason travelReason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.seatOut = seatOut;
        this.seatIn = seatIn;
        this.boardingNumOut = boardingNumOut;
        this.boardingNumIn = boardingNumIn;
        this.travelReason = travelReason;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSeatOut() {
        return seatOut;
    }

    public void setSeatOut(String seatOut) {
        this.seatOut = seatOut;
    }

    public String getSeatIn() {
        return seatIn;
    }

    public void setSeatIn(String seatIn) {
        this.seatIn = seatIn;
    }

    public String getBoardingNumOut() {
        return boardingNumOut;
    }

    public void setBoardingNumOut(String boardingNumOut) {
        this.boardingNumOut = boardingNumOut;
    }

    public String getBoardingNumIn() {
        return boardingNumIn;
    }

    public void setBoardingNumIn(String boardingNumIn) {
        this.boardingNumIn = boardingNumIn;
    }

    public TravelReason getTravelReason() {
        return travelReason;
    }

    public void setTravelReason(TravelReason travelReason) {
        this.travelReason = travelReason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.passengerId, this.firstName, this.lastName, this.email, this.dob,
                this.seatOut, this.seatIn, this.boardingNumOut, this.boardingNumIn, this.travelReason);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Passenger passengers))
            return false;
        return Objects.equals(this.passengerId, passengers.passengerId)
                && Objects.equals(this.firstName, passengers.firstName)
                && Objects.equals(this.lastName, passengers.lastName)
                && Objects.equals(this.email, passengers.email)
                && Objects.equals(this.dob, passengers.dob)
                && Objects.equals(this.seatOut, passengers.seatOut)
                && Objects.equals(this.seatIn, passengers.seatIn)
                && Objects.equals(this.boardingNumOut, passengers.boardingNumOut)
                && Objects.equals(this.boardingNumIn, passengers.boardingNumIn)
                && Objects.equals(this.travelReason, passengers.travelReason);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", seatOut='" + seatOut + '\'' +
                ", seatIn='" + seatIn + '\'' +
                ", boardingNumOut='" + boardingNumOut + '\'' +
                ", boardingNumIn='" + boardingNumIn + '\'' +
                ", travelReason=" + travelReason +
                '}';
    }
}
