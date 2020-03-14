# CarShopWebApp

Web Application to manage your Drivers, Cars and Routes.

Technologies used:
- Java 11 + Spring boot
- Spring Data JPA
- Thymeleaf
- H2

Models with CRUD and other functionalities:
- Driver
  + name, surname
  + list of routes planned for implementation or completed  
  + total distance of completed routes
- Car
  + brand, model, color (enum), year of production
  + list of routes planned for implementation or realized by the vehicle
- Route
  + name, start date, end date, start address, end address, realization (boolean)
  + distance and driving time of the planned route - calculated by external service (TomToM), 
  + there are two Route creators: based on geographical coordinates (working version) and with exact addres search (beta version),
  
Othe funcinalities:
- full vadiation of fields in froms
- REST interface for Driver
