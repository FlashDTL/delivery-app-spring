package com.example.demo;

import com.example.demo.entity.Observations;
import com.example.demo.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class DatabaseUpdator {

    private final StationRepository repository;

    @Autowired
    public DatabaseUpdator(StationRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 300000)
    public void addData() throws JAXBException, IOException {
        URL xmlUrl = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
        HttpURLConnection http = (HttpURLConnection) xmlUrl.openConnection();
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Observations.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Observations observations = (Observations) unmarshaller.unmarshal(is);
        List<Station> stations = observations.getStations();
        for (Station station : stations) {
            if (station.getName().equals("Tallinn-Harku") || station.getName().equals("Tartu-Tõravere") || station.getName().equals("Pärnu")){
                this.repository.save(new Station(station.getName(), station.getWmo_code(), station.getTemperature(), station.getWind_speed(), station.getPhenomenon(), observations.getTimestamp()));
            }
        }
    }
}
