package com.example.demo.entity;

import com.example.demo.entity.Station;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Observations {
    @XmlAttribute(name = "timestamp")
    private String timestamp;

    @XmlElement(name = "station")
     List<Station> stations;

    public Observations(List<Station> stations, String timestamp) {
        this.stations = stations;
        this.timestamp = timestamp;
    }

    public Observations() {
    }

    public List<Station> getStations() {
        return stations;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
