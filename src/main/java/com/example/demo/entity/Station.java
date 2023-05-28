package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

    @Id
    @GeneratedValue
    private Long id;
    @XmlElement(name="name")
    private String name;

    @XmlElement(name="wmocode")
    private long wmo_code;

    @XmlElement(name="airtemperature")
    private float temperature;

    @XmlElement(name="windspeed")
    private float wind_speed;

    @XmlElement(name="phenomenon")
    private String phenomenon;

    private String timestamp;


    public Station(String name, long wmo_code, float temperature, float wind_speed, String phenomenon, String timestamp) {
        this.name = name;
        this.wmo_code = wmo_code;
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.phenomenon = phenomenon;
        this.timestamp = timestamp;
    }
    public Station(){};


    public String getName() {
        return name;
    }

    public long getWmo_code() {
        return wmo_code;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public String getTimestamp() {return timestamp;}

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", wmo_code=" + wmo_code +
                ", temperature=" + temperature +
                ", wind_speed=" + wind_speed +
                ", phenomenom='" + phenomenon + '\'';
    }
}
