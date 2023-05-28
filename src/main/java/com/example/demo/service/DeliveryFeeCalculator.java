package com.example.demo.service;

import com.example.demo.StationRepository;
import com.example.demo.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFeeCalculator {

    StationRepository repository;

    @Autowired
    public DeliveryFeeCalculator() {
        this.repository = repository;
    }

    public double CalculateTotalFee(String city, String vehicle, StationRepository repository){
        return CalculateBaseFee(city, vehicle) + CalculateExtraFees(repository, vehicle, city);
    }

    public double CalculateBaseFee(String city, String vehicle) {
        if (city.equals("Tallinn-Harku")) {
            if (vehicle.equals("Car")) {
                return 4;
            } else if (vehicle.equals("Scooter")) {
                return 3.5;
            } else if (vehicle.equals("Bike")) {
                return 3;
            } else {
                throw new IllegalArgumentException("Invalid vehicle type");
            }
        } else if (city.equals("Tartu-Tõravere")) {
            if (vehicle.equals("Car")) {
                return 3.5;
            } else if (vehicle.equals("Scooter")) {
                return 3;
            } else if (vehicle.equals("Bike")) {
                return 2.5;
            } else {
                throw new IllegalArgumentException("Invalid vehicle type");
            }

        } else if (city.equals("Pärnu")) {
            if (vehicle.equals("Car")) {
                return 3;
            } else if (vehicle.equals("Scooter")) {
                return 2.5;
            } else if (vehicle.equals("Bike")) {
                return 2;
            } else {
                throw new IllegalArgumentException("Invalid vehicle type");
            }
        } else {
            throw new IllegalArgumentException("Invalid city");
        }
    }

    public double CalculateExtraFees(StationRepository repository, String vehicle, String city) {
        double fee = 0;
        List<Station> stations = repository.findStationsByName(city);
        Station station = stations.get(stations.size() - 1);

        if (vehicle.equals("Bike") || vehicle.equals("Scooter")) {
            if (station.getTemperature() < 10) {
                fee += 1;
            } else if (station.getTemperature() < 0) {
                fee += 0.5;
            }

            if (station.getPhenomenon().equals("snow") || station.getPhenomenon().equals("sleet")) {
                fee += 1;
            } else if (station.getPhenomenon().equals("rain")) {
                fee += 0.5;
            }

        }
        if (vehicle.equals("Bike")) {
            if (station.getWind_speed() >= 10 || station.getWind_speed() <= 20) {
                fee += 1;
            }
        }

        return fee;
    }
}
