package pl.altkom.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.altkom.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Driver;

import java.util.List;

@Controller
public class DriverManagerController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;

    @GetMapping("/createNewDriver")
    public String showNewDriverForm(Driver driver){

        return "newDriverForm";
    }

    @PostMapping("/saveNewDriver")
    public String processNewDriverForm(Driver driver){

        driverDao.save(driver);
        return "redirect:/";
    }

    @GetMapping("/getDrivers")
    public String showAllDriversList(final Model model){

        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDriversList", allDrivers);
        return "showAllDrivers";
    }
}
