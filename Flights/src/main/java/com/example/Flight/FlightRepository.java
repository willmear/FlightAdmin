package com.example.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(nativeQuery= true, value="SELECT * FROM flight WHERE destination = ?1")
    Collection<Flight> findByDestination(String destination);

}
