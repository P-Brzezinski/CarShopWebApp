package pl.brzezinski.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Address;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.service.tomTomApi.TomTomDirectionsApi;
import pl.brzezinski.CarShop.service.tomTomApi.TomTomSearchApi;

import java.io.*;
import java.util.List;

@Controller
public class RouteControllerForSearchApi {

    private TomTomSearchApi tomTomSearchApi = new TomTomSearchApi();
    private TomTomDirectionsApi tomTomDirectionsApi = new TomTomDirectionsApi();

    @Autowired
    DriverRepositoryDataJpaImpl driverDao;
    List<Driver> allDrivers;

    @Autowired
    CarRepositoryDataJpaImpl carDao;
    List<Car> allCars;

    @Autowired
    RouteRepositoryDataJpaImpl routeDao;

    @GetMapping("/newRouteForm")
    public String newRouteForm(Route route, final Model model) {
        getLists();
        model.addAttribute(route);
        model.addAttribute("allDrivers", allDrivers);
        model.addAttribute("allCars", allCars);
        System.out.println("newRouteForm >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +  route.toString());
        return "routeFormForSearchApi";
    }

    @GetMapping("/searchStartAddress")
    public String searchStartAddress(Route route,
                                     @RequestParam String query,
                                     final Model model) throws IOException {
        String createQuery = query.replace(" ", "%20");

        List<Address> startAddressList = tomTomSearchApi.executeQuery(createQuery);

        model.addAttribute("allDrivers", allDrivers);
        model.addAttribute("allCars", allCars);
        model.addAttribute("startAddressList", startAddressList);
        model.addAttribute(route);
        System.out.println("searchStartAddress >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +  route.toString());
        return "routeFormForSearchApi";
    }

    @GetMapping("/searchEndAddress")
    public String searchEndAddress(Route route,
                                     @RequestParam String query,
                                     final Model model) throws IOException {
        String createQuery = query.replace(" ", "%20");

        List<Address> endAddressList = tomTomSearchApi.executeQuery(createQuery);
    
        model.addAttribute("allDrivers", allDrivers);
        model.addAttribute("allCars", allCars);
        model.addAttribute("endAddressList", endAddressList);
        model.addAttribute(route);
        System.out.println("searchEndAddress >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +  route.toString());
        return "routeFormForSearchApi";
    }

    @GetMapping("/saveRoute")
    public String saveRoute(Route route) throws IOException {
        Long driverId = route.getDriverId();
        Driver driver = driverDao.getOne(driverId);
        Long carId = route.getCarId();
        Car car = carDao.getOne(carId);
        driver.addRoute(route);
        car.addRoute(route);
        System.out.println(route.toString());
        tomTomDirectionsApi.processRouteWithDataFromTomTom(route);
        routeDao.save(route); // insert do tabeli Route z driver_id = null
        driverDao.save(driver); // update na tabeli Route z wpisaniem driver_id
        carDao.save(car); // update na tabeli Route z wpisaniem car_id
        System.out.println(route.toString());
        return "redirect:/";
    }

    private void getLists(){
        this.allDrivers = driverDao.findAll();
        this.allCars = carDao.findAll();
    }
}
