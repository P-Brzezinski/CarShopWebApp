package pl.brzezinski.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.model.enums.Color;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarManagerController {

    @Autowired
    private CarRepositoryDataJpaImpl carDao;

    @GetMapping("/submitCarForm")
    public String createOrEditCar(@RequestParam(name = "carId", required = false) Long carId, final Model model, Car car) {
        if(carId != null){
            car = carDao.getOne(carId);
            model.addAttribute(car);
        }
        model.addAttribute("colors", Color.values());
        return "carForm";
    }

    @PostMapping("/saveOrEditCar")
    public String saveOrEditCar(@Valid Car car, BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("colors", Color.values());
            return "carForm";
        }
        carDao.save(car);
        return "redirect:/";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam(name = "carId") Long id) {
        carDao.deleteById(id);
        return "redirect:/showAllCars";
    }

    @GetMapping("/showAllCars")
    public String showAllCars(final Model model) {
        List<Car> allCars = carDao.findAll();
        model.addAttribute("cars", allCars);
        return "showAllCars";
    }

    @GetMapping("/showCarAssignedRoutes")
    public String showAssignedRoutes(@RequestParam(name = "carId") Long carId, final Model model){
        Car car = carDao.getOne(carId);
        List<Route> routes = car.getRoutes();
        model.addAttribute("routes", routes);
        return "routesAssignedToCar";
    }
}
