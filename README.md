# Delivery App - Spring & React

## Hello there!

This project is a small food delivery application developed using the Spring Boot (backend) and React.js (frontend). It calculates the delivery fee based on regional base fee, vehicle type and weather conditions. <br>

The weather information is retrieved from the Estonian Environment Agency at https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php <br>

To keep the weather data up-to-date, a CronJob is implemented to request the weather data every 5 minutes and store the latest weather conditions in an in-memory database. 
Newly added weather entries do not overwrite the existing ones.
When a user requests the delivery fee calculation or wants to view the weather conditions, they will receive the most recent data available. <br>

All calculations and data manipulation are performed on the backend of the application.

## Delivery Fee Calculation
The total delivery fee consists of a regional
base fee for a specific vehicle types and extra fees for some weather conditions: <br>

### Regional Base Fee (RBF)
Tallinn: <br>
- Car: RBF = 4 €
- Scooter: RBF = 3.5 €
- Bike: RBF = 3 €

Tartu:

- Car: RBF = 3.5 €
- Scooter: RBF = 3 €
- Bike: RBF = 2.5 €

Pärnu:
- Car: RBF = 3 €
- Scooter: RBF = 2.5 €
- Bike: RBF = 2 €

### Extra Fees for Weather Conditions
Extra fee based on air temperature (ATEF):
- For Scooter or Bike:
  - If the air temperature is less than -10̊ C, ATEF = 1 €
  - If the air temperature is between -10̊ C and 0̊ C, ATEF = 0.5 €

Extra fee based on wind speed (WSEF):
- For Bike:
  - If the wind speed is between 10 m/s and 20 m/s, WSEF = 0.5 €
  - If the wind speed is greater than 20 m/s, an error message "Usage of selected vehicle type is forbidden" is displayed.
 
Extra fee based on weather phenomenon (WPEF):
- For Scooter or Bike:
  - If the weather phenomenon is related to snow or sleet, WPEF = 1 €
  - If the weather phenomenon is related to rain, WPEF = 0.5 €
  - If the weather phenomenon is glaze, hail, or thunder, an error message "Usage of selected vehicle type is forbidden" is displayed.

## Example calculation 
- Input parameters: TARTU and BIKE -> RBF = 2,5 €
- Latest weather data for Tartu (Tartu-Tõravere):
  - Air temperature = -2,1̊ C -> ATEF = 0,5 €
  - Wind speed = 4,7 m/s -> WSEF = 0 €
  - Weather phenomenon = Light snow shower -> WPEF = 1 €
- Total delivery fee = RBF + ATEF + WSEF + WPEF = 2,5 + 0,5 + 0 + 1 = 4 €

## Technologies Used
- Java
- Spring Boot
- React.js
- H2 Database

## Prerequisites
Java Development Kit (JDK) - Version 8 or higher <br>
Node.js - Latest stable version

## Backend Setup
1. Clone the repository: <br>
`git clone https://github.com/FlashDTL/delivery-app-spring.git`

2. Open a terminal and navigate to the project's root directory: <br>
`cd delivery-app-spring`

3. Build the backend using Gradle: <br>
`./gradlew build`

4. Run the backend application: <br>
`./gradlew bootRun`

## Frontend Setup
1. Open a new terminal and navigate to the project's frontend directory: <br>
`cd delivery-app-spring/frontend`

2. Install the required frontend dependencies: <br>
`npm install`

3. Start the frontend development server: <br>
`npm start`

## Usage
Once both the backend and frontend are up and running, you can access the delivery application by visiting http://localhost:3000 in your web browser. <br>

<p align="center">
  Delivery fee calculation <br>
  <img src="https://i.imgur.com/1n1GYdW.png" alt="Fee" width="400">
</p>

<p align="center">
  Weather information <br>
  <img src="https://i.imgur.com/jVNtKLF.png" alt="Weather" width="400">
</p>


