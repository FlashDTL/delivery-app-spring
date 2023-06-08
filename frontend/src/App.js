import './style/phone.css';
import './style/app.css';
import React, { useState } from 'react';

function App() {
    const [fee, setFee] = useState(0);
    const [city, setCity] = useState('');
    const [vehicle, setVehicle] = useState('');
    const [weather, setWeather] = useState('');
    const [display, setDisplay] = useState(true);


    const getDeliveryFee = (city, vehicle) => {
        if (city === '' || vehicle === ''){
            return ''
        }
        const url = "http://localhost:8080/delivery/fee?city=" + city + "&vehicle=" + vehicle
        fetch(url)
            .then(response => {
                setDisplay(true);
                return response.json()
            })
            .then(fee => {
                setFee(fee)
            })
    }

    const getWeather = (city) => {
        if (city === ''){
            return ''
        }
        const url = "http://localhost:8080/delivery/info?city=" + city
        fetch(url)
            .then(response => {
                setDisplay(false)
                return response.json()
            })
            .then(weather => {
                setWeather(weather)
            })
    }

  return (
    <div className="smartphone">
      <div className="content">
          {display ?
              <div className="deliveryFee">
                  <h1>Delivery fee</h1>
                  <p>{fee}€</p>
              </div> :

              <div className="weatherInfo">
                  <h1>{weather.name}</h1>
                  <p>Temperature: {weather.temperature}°</p>
                  <p>Wind speed: {weather.wind_speed} m/s</p>
                  <p>Phenomenon: {weather.phenomenon}</p>
              </div>
          }
          <div className="options">
              <p>Cities</p>
              <div className="stations">
                  <button onClick={ () => setCity("Tartu-Tõravere")} className={city === "Tartu-Tõravere" ? "selected" : ''}>Tartu</button>
                  <button onClick={ () => setCity("Tallinn-Harku")} className={city === "Tallinn-Harku" ? "selected" : ''}>Tallinn</button>
                  <button onClick={ () => setCity("Pärnu")} className={city === "Pärnu" ? "selected" : ''}>Pärnu</button>
              </div>

              <p>Vehicle type</p>
              <div className="vehicle">
                  <button onClick={() => setVehicle("Car")} className={vehicle === "Car" ? "selected" : ''}>Car</button>
                  <button onClick={() => setVehicle("Scooter")} className={vehicle === "Scooter" ? "selected" : ''}>Scooter</button>
                  <button onClick={() => setVehicle("Bike")} className={vehicle === "Bike" ? "selected" : ''}>Bike</button>
              </div>
              <div className="submit-info">
                  <button onClick={() => getDeliveryFee(city, vehicle)}>Calculate</button>
                  <button onClick={() => getWeather(city)}>Weather</button>
              </div>
          </div>
      </div>
    </div>
  );
}

export default App;
