package pl.altkom.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.altkom.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.altkom.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.altkom.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Car;
import pl.altkom.CarShop.model.Driver;
import pl.altkom.CarShop.model.Route;

import java.util.List;

@Controller
public class RouteManagerController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;

    @Autowired
    private CarRepositoryDataJpaImpl carDao;

    @Autowired
    private RouteRepositoryDataJpaImpl routeDao;

    @GetMapping("/createNewRoute")
    public String showNewRouteForm(final Model model, Route route){

        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDrivers", allDrivers);

        List<Car> allCars = carDao.findAll();
        model.addAttribute("allCars", allCars);
        return "newRouteForm";
    }

    @PostMapping("/saveRoute")
    public String processNewRouteForm(final Route route){

        // 1. Pobrac konretnego kierowce - wg id z 'route'
        Long driverId = route.getDriverId();// tylko dla formularza
        Driver driver = driverDao.getOne(driverId);

        Long carId = route.getCarId();
        Car car = carDao.getOne(carId);

        // 2. Dla pobranego kierowcy dodajemy trase i do samochodu trase
        driver.addRoute(route);
        car.addRoute(route);

        // 3. zapisujemy trase, kierowce, pojazd
        routeDao.save(route); // insert do tabeli Route z driver_id = null
        driverDao.save(driver); // update na tabeli Route z wpisaniem driver_id
        carDao.save(car); // update na tabeli Route z wpisaniem car_id

        return "redirect:/";
    }

    @GetMapping("/showRoutesByDriver")
    public String showRoutesByUserId(final Model model, @RequestParam Long driverId){

        Driver driver = driverDao.getOne(driverId);

        List<Route> routes = driver.getRoutes();
        model.addAttribute("allRoutes", routes);
        return "showAllRoutesByDriver";
    }
}
