package com.example.Flight.Passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Return all Passengers
    @GetMapping("/passengers")
    CollectionModel<EntityModel<Passenger>> all() {
        return passengerService.all();
    }

    // Add a Passenger
    @PostMapping("/passengers")
    ResponseEntity<?> addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    // Get one Passenger
    @GetMapping("/passengers/{id}")
    EntityModel<Passenger> one(@PathVariable Long id) {
        return passengerService.one(id);
    }

    // Delete a Passenger
    @DeleteMapping("/passengers/{id}")
    ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        return passengerService.deletePassenger(id);
    }


}