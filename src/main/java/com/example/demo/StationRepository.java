package com.example.demo;

import com.example.demo.entity.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationRepository extends CrudRepository<Station, Long> {
    List<Station> findStationsByName(String name);

}
