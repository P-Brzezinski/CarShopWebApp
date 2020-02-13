package pl.altkom.CarShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altkom.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.altkom.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Car;
import pl.altkom.CarShop.model.Driver;
import pl.altkom.CarShop.model.enums.Color;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class DataGenerator {

    @Autowired
    private CarRepositoryDataJpaImpl carDao;
    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;

    @PostConstruct
    public void createCarData(){
        carDao.save(new Car("Audi", "Astra", Color.RED, LocalDate.of(2006,10,12), "33344455566677788"));
        carDao.save(new Car("Opel", "Corsa", Color.RED, LocalDate.of(2003,12,21), "33344455566677788"));
        carDao.save(new Car("Toyota", "RAV4", Color.GREY, LocalDate.of(2005,8,30), "33344455566677788"));
        carDao.save(new Car("Volkswagen", "Golf", Color.BLACK, LocalDate.of(1998,3,27), "33344455566677788"));
        carDao.save(new Car("Skoda", "Fabia", Color.BLUE, LocalDate.of(1995,1,3), "33344455566677788"));
        carDao.save(new Car("Seat", "Cordoba", Color.GREEN, LocalDate.of(2003,8,4), "33344455566677788"));
        carDao.save(new Car("Ferrari", "Astra", Color.RED, LocalDate.of(2019,1,12), "33344455566677788"));
        carDao.save(new Car("Ferrari", "Testarossa", Color.WHITE, LocalDate.of(2005,8,4), "33344455566677788"));
        carDao.save(new Car("Ferrari", "California", Color.BLACK, LocalDate.of(2012,5,12), "33344455566677788"));
        carDao.save(new Car("Land Rover", "Vellar", Color.GREY, LocalDate.of(2020,1,12), "33344455566677788"));
        carDao.save(new Car("Peugot", "206", Color.GREEN, LocalDate.of(2001,1,12), "33344455566677788"));
        carDao.save(new Car("Peugot", "207", Color.WHITE, LocalDate.of(2002,11,12), "33344455566677788"));
        carDao.save(new Car("Ford", "Focus", Color.BLUE, LocalDate.of(2012,11,12), "33344455566677788"));
        carDao.save(new Car("Ford", "Mustand", Color.YELLOW, LocalDate.of(2016,1,12), "33344455566677788"));
    }

    @PostConstruct
    public void createDriversData(){
        driverDao.save(new Driver("Jan", "Kowalski"));
        driverDao.save(new Driver("Adam", "Adamski"));
    }

}
