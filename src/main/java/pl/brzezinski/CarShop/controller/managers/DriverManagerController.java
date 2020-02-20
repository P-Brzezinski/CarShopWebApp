package pl.brzezinski.CarShop.controller.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DriverManagerController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;

    @GetMapping("/submitDriverForm")
    public String createOrEditDriver(@RequestParam(name = "driverId", required = false) Long driverId, final Model model, Driver driver){
        if (driverId != null){
            driver = driverDao.getOne(driverId);
            model.addAttribute(driver);
        }
        return "driverForm";
    }

    @PostMapping("/saveOrEditDriver")
    public String saveOrEditDriver(@Valid Driver driver, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "driverForm";
        }
        driverDao.save(driver);
        return "redirect:/";
    }

    @GetMapping("/showAllDrivers")
    public String showAllDrivers(final Model model){
        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDriversList", allDrivers);
        return "showAllDrivers";
    }

    @GetMapping("/showRoutesByDriverId")
    public String showRoutesByDriverId(final Model model, @RequestParam(name = "driverId") Long driverId) {
        Driver driver = driverDao.getOne(driverId);
        List<Route> routes = driver.getRoutes();
        model.addAttribute("allRoutes", routes);

        for (Route route : routes) {
            System.out.println(route.getRouteName());
        }

        return "showAllRoutesByDriver";
    }

    @GetMapping("/deleteDriverById")
    public String deleteDriverById(@RequestParam(name = "driverId") Long driverId){
        driverDao.deleteById(driverId);
        return "redirect:showAllDrivers";
    }
}
