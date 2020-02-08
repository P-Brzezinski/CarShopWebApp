package pl.altkom.CarShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altkom.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Car;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class DataGenerator {

    @Autowired
    private CarRepositoryDataJpaImpl dao;

    @PostConstruct
    public void createSomeData(){
        dao.save(new Car("Audi", "Astra", "red", LocalDate.of(2006,10,12), "33344455566677788"));
        dao.save(new Car("Opel", "Corsa", "red", LocalDate.of(2003,12,21), "33344455566677788"));
        dao.save(new Car("Toyota", "RAV4", "grey", LocalDate.of(2005,8,30), "33344455566677788"));
        dao.save(new Car("Volkswagen", "Golf", "black", LocalDate.of(1998,3,27), "33344455566677788"));
        dao.save(new Car("Skoda", "Fabia", "blue", LocalDate.of(1995,1,3), "33344455566677788"));
        dao.save(new Car("Seat", "Cordoba", "green", LocalDate.of(2003,8,4), "33344455566677788"));
        dao.save(new Car("Ferrari", "Astra", "red", LocalDate.of(2019,1,12), "33344455566677788"));
        dao.save(new Car("Ferrari", "Testarossa", "pink", LocalDate.of(2005,8,4), "33344455566677788"));
        dao.save(new Car("Ferrari", "California", "black", LocalDate.of(2012,5,12), "33344455566677788"));
        dao.save(new Car("Land Rover", "Vellar", "grey", LocalDate.of(2020,1,12), "33344455566677788"));
        dao.save(new Car("Peugot", "206", "grey", LocalDate.of(2001,1,12), "33344455566677788"));
        dao.save(new Car("Peugot", "207", "yellow", LocalDate.of(2002,11,12), "33344455566677788"));
        dao.save(new Car("Ford", "Focus", "blue", LocalDate.of(2012,11,12), "33344455566677788"));
        dao.save(new Car("Ford", "Mustand", "yellow", LocalDate.of(2016,1,12), "33344455566677788"));
    }
}
