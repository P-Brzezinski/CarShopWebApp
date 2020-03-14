package pl.brzezinski.CarShop.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Driver;

import java.util.List;

@RestController
public class DriverRestController {

    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;

    @GetMapping(path = "/drivers")
    public List<Driver> allDrivers(){
        return driverDao.findAll();
    }

    @GetMapping(path = "/driver/firstName/{firstName}")
    public Driver searchDriverByFirstName(@PathVariable("firstName") String firstName){
        return driverDao.findDriverByFirstName(firstName);
    }

    @GetMapping(path = "/driver/lastName/{lastName}")
    public Driver searchDriverByLastName(@PathVariable("lastName") String lastName) {
        return driverDao.findDriverByLastName(lastName);
    }

    @GetMapping(path = "/drivers/{id}")
    public Driver oneDriver(@PathVariable("id") Long id){
        return driverDao.getOne(id);
    }

    @DeleteMapping(path = "/drivers/{id}")
    public String removeDriver(@PathVariable("id") Long id){
        driverDao.deleteById(id);
        return "Driver with id= " + id + " was deleted";
    }

    @PostMapping("/drivers")
    public Driver addDriver(@RequestBody Driver driver)
    {
        driverDao.save(driver);
        return driver;
    }

    @PutMapping("/drivers")
    public Driver updateDriver(@RequestBody Driver driver)
    {
        driverDao.save(driver);
        return driver;
    }
}
