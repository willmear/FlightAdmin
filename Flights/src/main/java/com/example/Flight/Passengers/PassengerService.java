package com.example.Flight.Passengers;

import ExceptionHandling.PassengerNotFoundException;
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
public class PassengerService {

    private final PassengerRepository repo;
    private final PassengerModelAssembler assembler;


    public PassengerService(PassengerRepository repo, PassengerModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    // Return all Passengers
     CollectionModel<EntityModel<Passenger>> all() {
         List<EntityModel<Passenger>> passengers = repo.findAll().stream()
                 .map(assembler::toModel)
                 .collect(Collectors.toList());
         return CollectionModel.of(passengers,
                 linkTo(methodOn(PassengerService.class).all()).withSelfRel());

     }

    // Add a Passenger
    ResponseEntity<?> addPassenger(Passenger passenger) {
         EntityModel<Passenger> entityModel = assembler.toModel(repo.save(passenger));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Get one Passenger
    EntityModel<Passenger> one(Long id) {
        Passenger passenger = repo.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
        return EntityModel.of(passenger,
                linkTo(methodOn(PassengerController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PassengerController.class).all()).withRel("passengers"));
    }

    // Delete a Passenger
    ResponseEntity<?> deletePassenger(Long id) {

         repo.deleteById(id);

         return ResponseEntity.noContent().build();
    }
}
