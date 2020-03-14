package pl.brzezinski.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.service.tomTomApi.TomTomDirectionsApi;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class RouteManagerController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;
    @Autowired
    private CarRepositoryDataJpaImpl carDao;
    @Autowired
    private RouteRepositoryDataJpaImpl routeDao;

    private TomTomDirectionsApi tomTomDirectionsApi = new TomTomDirectionsApi();

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

    //TODO make this method shorter!
    @PostMapping("/saveRoute")
    public String processNewRouteForm(@Valid Route route, BindingResult bindingResult, final Model model) throws IOException {
        if(bindingResult.hasErrors()){
            List<Driver> allDrivers = driverDao.findAll();
            model.addAttribute("allDrivers", allDrivers);
            List<Car> allCars = carDao.findAll();
            model.addAttribute("allCars", allCars);
            return "routeForm";
        }

        if (route.getId() != null) {
            Route routeFromDao = routeDao.findById(route.getId()).get();
            routeFromDao.setId(route.getId());
            routeFromDao.setRouteName(route.getRouteName());
            routeFromDao.setStartDateTime(route.getStartDateTime());
            routeFromDao.setStartAddress(route.getStartAddress());
            routeFromDao.setEndAddress(route.getEndAddress());

            tomTomDirectionsApi.processRouteWithDataFromTomTom(route);

            routeFromDao.setDriverId(route.getDriverId());
            routeFromDao.setCarId(route.getCarId());

            Driver driver = driverDao.getOne(route.getDriverId());
            Car car = carDao.getOne(route.getCarId());

            driver.addRoute(routeFromDao);
            car.addRoute(routeFromDao);

            routeDao.save(routeFromDao);
            driverDao.save(driver);
            carDao.save(car);
        }else {
            Long driverId = route.getDriverId();
            Driver driver = driverDao.getOne(driverId);

            Long carId = route.getCarId();
            Car car = carDao.getOne(carId);

            driver.addRoute(route);
            car.addRoute(route);

            tomTomDirectionsApi.processRouteWithDataFromTomTom(route);

            routeDao.save(route);
            driverDao.save(driver);
            carDao.save(car);
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

    @GetMapping("/markAsDone")
    public String markAsDone(@RequestParam Long routeId){
        List<Driver> allDrivers = driverDao.findAll();
        List<Route> allRoutes;
        for (Driver driver : allDrivers){
            allRoutes = driver.getRoutes();
            for (Route route : allRoutes){
                if (route.getId() == routeId){
                    Long sum = Long.sum(driver.getDistanceTaken(), route.getDistance());
                    driver.setDistanceTaken(sum);
                    driverDao.save(driver);
                }
            }
        }
        return "redirect:/showAllRoutes";
    }
}
