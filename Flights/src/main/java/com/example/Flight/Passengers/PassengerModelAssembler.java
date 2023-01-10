package com.example.Flight.Passengers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PassengerModelAssembler
        implements RepresentationModelAssembler<Passenger, EntityModel<Passenger>> {

    @Override
    public EntityModel<Passenger> toModel(Passenger passenger) {
        return EntityModel.of(passenger,
                linkTo(methodOn(PassengerController.class).one(passenger.getPassengerId())).withSelfRel(),
                linkTo(methodOn(PassengerController.class).all()).withRel("passengers"));
    }
}
