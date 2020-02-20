package pl.brzezinski.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;

import java.util.List;

@Controller
public class RouteManagerController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;
    @Autowired
    private CarRepositoryDataJpaImpl carDao;
    @Autowired
    private RouteRepositoryDataJpaImpl routeDao;

    @GetMapping("/submitRouteForm")
    public String submitRouteForm(@RequestParam(name = "routeId", required = false) Long routeId, Route route, final Model model) {
        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDrivers", allDrivers);

        List<Car> allCars = carDao.findAll();
        model.addAttribute("allCars", allCars);

        if (routeId != null){
            route = routeDao.findById(routeId).get();
            route.setId(routeId);
            model.addAttribute(route);
        }

        return "routeForm";
    }

    @PostMapping("/saveRoute")
    public String processNewRouteForm(final Route route) {

        if (route.getId() != null) {
            Route routeFromDao = routeDao.findById(route.getId()).get();
            routeFromDao.setId(route.getId());
            routeFromDao.setRouteName(route.getRouteName());
            routeFromDao.setStartDate(route.getStartDate());
            routeFromDao.setPlannedEndDate(route.getPlannedEndDate());
            routeFromDao.setStartTime(route.getStartTime());
            routeFromDao.setPlannedEndTime(route.getPlannedEndTime());
            routeFromDao.setStartAddress(route.getStartAddress());
            routeFromDao.setEndAddress(route.getEndAddress());

            //bez dwóch poniższych setterów Driver Assigned i Car Assigned w tabeli All Roads (w przegladarce) sie nie zmienie,
            //ale w MySQL już tak, dlaczego?
            routeFromDao.setDriverId(route.getDriverId());
            routeFromDao.setCarId(route.getCarId());

            Driver driver = driverDao.getOne(route.getDriverId());
            Car car = carDao.getOne(route.getCarId());

            //dlaczego po dodaniu nowej trasy stara znika?
            //nie widać starej trasy w polu 'routes' w modelu Driver
            driver.addRoute(routeFromDao);
            car.addRoute(routeFromDao);

            routeDao.save(routeFromDao);
            driverDao.save(driver);
            carDao.save(car);
        }else {
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
        }
        return "redirect:/";
    }

    @GetMapping("/showAllRoutes")
    public String showAllRoutes(final Model model) {
        List<Route> allRoutes = routeDao.findAll();
        model.addAttribute("allRoutes", allRoutes);
        return "showAllRoutes";
    }

    @GetMapping("/deleteRoute")
    public String deleteRoute(@RequestParam Long routeId) {
        routeDao.deleteById(routeId);
        return "redirect:/showAllRoutes";
    }

    @GetMapping("/editRoute")
    public String editRoute(@RequestParam Long routeId, Route route, final Model model) {

        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDrivers", allDrivers);

        List<Car> allCars = carDao.findAll();
        model.addAttribute("allCars", allCars);

        route = routeDao.getOne(routeId);
        route.setId(routeId);
        model.addAttribute(route);

        return "editRouteForm";
    }

    @PostMapping("/processEditRouteForm")
    public String processEditRouteForm(Route route) {
        routeDao.save(route);
        return "redirect:/";
    }
}
