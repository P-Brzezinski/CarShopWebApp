package pl.altkom.CarShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.altkom.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Car;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private CarRepositoryDataJpaImpl dao;

    @GetMapping("/saveCar")
    public String showCarForm(Car car) {
        return "newCarForm";
    }

    @PostMapping("/saveCar")
    public String processCarForm(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "newCarForm";
        }
        dao.save(car);
        return "showSavedCar";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam(name = "carId") Long id) {
        dao.deleteById(id);
        return "redirect:/getCars";
    }

    @GetMapping("/getCars")
    public String showAllCarsList(final Model model) {
        List<Car> carList = dao.findAll();
        model.addAttribute("cars", carList);
        return "showAllCars";
    }

    @GetMapping("/editCar")
    public String editCar(@RequestParam(name = "carId") Long carId, Car car) {
        car.setId(carId);
        return "editCarForm";
    }

    @PostMapping("/editCar")
    public String editCarForm(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "editCarForm";
        }
        dao.save(car);
        return "index";
    }
}
