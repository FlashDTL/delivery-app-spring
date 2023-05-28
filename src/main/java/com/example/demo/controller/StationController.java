package com.example.demo.controller;

import com.example.demo.service.DeliveryFeeCalculator;
import com.example.demo.entity.Station;
import com.example.demo.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class StationController {

    private final StationRepository repository;

    @Autowired
    public StationController(StationRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/fee")
    public ResponseEntity<Double> getDeliveryFee(@RequestParam String city, @RequestParam String vehicle) {
        try {
            DeliveryFeeCalculator calculator = new DeliveryFeeCalculator();
            return ResponseEntity.ok(calculator.CalculateTotalFee(city, vehicle, repository));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<Station> getStationInfo(@RequestParam String city){
        try {
            List<Station> stations = repository.findStationsByName(city);
            Station station = stations.get(stations.size()-1);
            return ResponseEntity.ok(station);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //http://localhost:8080/delivery/fee?city=P%C3%A4rnu&vehicle=Bike
}
