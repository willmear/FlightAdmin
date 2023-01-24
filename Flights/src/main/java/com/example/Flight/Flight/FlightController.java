package com.example.Flight.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Return all Flights
    @GetMapping("/flights")
    CollectionModel<EntityModel<Flight>> all(){
        return flightService.all();
    }

    // Add new Flight
    @PostMapping("/flights")
    ResponseEntity<?> addNewFlight(@RequestBody Flight newFlight) {

        return flightService.addNewFlight(newFlight);
    }

    // Return Flight with given id
    @GetMapping("/flights/{id}")
    EntityModel<Flight> one(@PathVariable Long id) {
        return flightService.one(id);
    }

    // Update Flight with given Flight id
    @PutMapping("/flights/{flightId}")
    ResponseEntity<?> newFlightId(@RequestBody Flight newFlight,
                       @PathVariable Long flightId) {
        return flightService.newFlightId(newFlight, flightId);
    }

    // Create relationship between Flight and Passenger
    @PutMapping("/flights/{flightId}/{passengerId}")
    ResponseEntity<?> flightToPassenger(
            @PathVariable Long flightId,
            @PathVariable Long passengerId
    ) {
        return flightService.flightToPassenger(flightId, passengerId);
    }

    // Delete Flight
    @DeleteMapping("/flights/{id}")
    ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        return flightService.deleteFlight(id);
    }

    // Return Flights with given Flight Destination
    @GetMapping("/flights/get/{destination}")
    CollectionModel<EntityModel<Flight>> byDestination(@PathVariable String destination) {
        return flightService.toDestination(destination);
    }

}
