package com.example.Flight;

import ExceptionHandling.FlightNotFoundException;
import com.example.Flight.Passengers.Passenger;
import com.example.Flight.Passengers.PassengerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FlightService {

    private final FlightRepository repo;
    private final PassengerRepository passengerRepo;
    private final FlightModelAssembler assembler;

    public FlightService(FlightRepository repo,
                         PassengerRepository passengerRepo, FlightModelAssembler assembler) {
        this.repo = repo;
        this.passengerRepo = passengerRepo;
        this.assembler = assembler;
    }

    // Return all Flights
    CollectionModel<EntityModel<Flight>> all() {
        List<EntityModel<Flight>> flights = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(flights,
                linkTo(methodOn(FlightController.class).all()).withSelfRel());

    }

    // Add new Flight
    ResponseEntity<?> addNewFlight(Flight newFlight) {

        EntityModel<Flight> entityModel = assembler.toModel(repo.save(newFlight));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Return Flight with given id
    EntityModel<Flight> one(Long id) {
        Flight flight = repo.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        return assembler.toModel(flight);

    }

    // Update Flight with given Flight id
    ResponseEntity<?> newFlightId(Flight newFlight, Long flightId) {

        Flight updatedFlight = repo.findById(flightId)
                .map(flight -> {
                    flight.setDepartFrom(newFlight.getDepartFrom());
                    flight.setDestination(newFlight.getDestination());
                    flight.setDepartTime(newFlight.getDepartTime());
                    flight.setDestinationTime(newFlight.getDestinationTime());
                    flight.setSeats(newFlight.getSeats());
                    return repo.save(flight);
                })
                .orElseGet(() -> {
                    newFlight.setFlightId(flightId);
                    return repo.save(newFlight);
                });
        EntityModel<Flight> entityModel = assembler.toModel(updatedFlight);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Create relationship between Flight and Passenger
    public ResponseEntity<?> flightToPassenger(Long flightId, Long passengerId) {
        Flight flight = repo.findById(flightId).orElse(null);
        Passenger passenger = passengerRepo.findById(passengerId).orElse(null);
        assert flight != null;
        flight.addPassenger(passenger);
        repo.save(flight);

        EntityModel<Flight> entityModel = assembler.toModel(flight);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Delete Flight
    ResponseEntity<?> deleteFlight(Long id) {

        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // Return Flights with given Flight Destination
    public CollectionModel<EntityModel<Flight>> toDestination(String destination) {
        String correctFormat = destination.substring(0, 1).toUpperCase() + destination.substring(1);
        List<EntityModel<Flight>> flights = repo.findByDestination(correctFormat).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(flights,
                linkTo(methodOn(FlightController.class).byDestination(destination)).withSelfRel());
    }

}
