package com.example.Flight.Flight;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class FlightModelAssembler
        implements RepresentationModelAssembler<Flight, EntityModel<Flight>> {

    @Override
    public EntityModel<Flight> toModel(Flight flight) {
        return EntityModel.of(flight,
                linkTo(methodOn(FlightController.class).one(flight.getFlightId())).withSelfRel(),
                linkTo(methodOn(FlightController.class).all()).withRel("flights"));
    }
}
