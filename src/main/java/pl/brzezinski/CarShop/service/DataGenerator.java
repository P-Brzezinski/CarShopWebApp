package pl.brzezinski.CarShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.model.enums.Color;
import pl.brzezinski.CarShop.service.tomTomApi.TomTomApi;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class DataGenerator {

    @Autowired
    private CarRepositoryDataJpaImpl carDao;
    @Autowired
    private DriverRepositoryDataJpaImpl driverDao;
    @Autowired
    private RouteRepositoryDataJpaImpl routeDao;

    TomTomApi tomTomApi = new TomTomApi();

    Driver driverOne = new Driver("Jan", "Kowalski");
    Driver driverTwo = new Driver("Adam", "Adamski");

    Car carOne = new Car("Audi", "Astra", Color.RED, 2020, "33344455566677788");
    Car carTwo = new Car("Toyota", "RAV4", Color.GREY, 2005, "33344455566677788");

    @PostConstruct
    public void createCarData() {
        carDao.save(carOne);
        carDao.save(carTwo);
        carDao.save(new Car("Opel", "Corsa", Color.RED, 2003, "33344455566677788"));
        carDao.save(new Car("Toyota", "RAV4", Color.GREY, 2005, "33344455566677788"));
        carDao.save(new Car("Volkswagen", "Golf", Color.BLACK, 1998, "33344455566677788"));
        carDao.save(new Car("Skoda", "Fabia", Color.BLUE, 1995, "33344455566677788"));
        carDao.save(new Car("Seat", "Cordoba", Color.GREEN, 2003, "33344455566677788"));
        carDao.save(new Car("Ferrari", "Astra", Color.RED, 2019, "33344455566677788"));
        carDao.save(new Car("Ferrari", "Testarossa", Color.WHITE, 2005, "33344455566677788"));
        carDao.save(new Car("Ferrari", "California", Color.BLACK, 2012, "33344455566677788"));
        carDao.save(new Car("Land Rover", "Vellar", Color.GREY, 2020, "33344455566677788"));
        carDao.save(new Car("Peugot", "206", Color.GREEN, 2001, "33344455566677788"));
        carDao.save(new Car("Peugot", "207", Color.WHITE, 2002, "33344455566677788"));
        carDao.save(new Car("Ford", "Focus", Color.BLUE, 2012, "33344455566677788"));
        carDao.save(new Car("Ford", "Mustand", Color.YELLOW, 2016, "33344455566677788"));
    }

    @PostConstruct
    public void createDriversData() {
        driverDao.save(driverOne);
        driverOne.setDistanceTaken(Long.valueOf(0));
        driverDao.save(driverTwo);
        driverTwo.setDistanceTaken(Long.valueOf(0));
    }

    //TODO split method to smaller methods
    @PostConstruct
    public void createRouteDataAndAssignToDriverAndCar() throws IOException {

        Route routeOne = new Route("Google Poland -> Google Russia",
                LocalDateTime.of(2021, 1, 1, 12,00),
                "51.117559,17.041687",
                "55.746883,37.626551",
                driverOne.getId(),
                carOne.getId());

        tomTomApi.processRouteWithDataFromTomTom(routeOne);
        driverOne.addRoute(routeOne);
        carOne.addRoute(routeOne);
        routeDao.save(routeOne);
        driverDao.save(driverOne);
        carDao.save(carOne);

        Route routeTwo = new Route("Google Poland -> Google France",
                LocalDateTime.of(2021, 12, 10, 12, 0),
                "51.117559,17.041687",
                "48.877135,2.330230",
                Long.valueOf(driverTwo.getId()),
                Long.valueOf(carTwo.getId()));

        tomTomApi.processRouteWithDataFromTomTom(routeTwo);
        driverTwo.addRoute(routeTwo);
        carTwo.addRoute(routeTwo);
        routeDao.save(routeTwo);
        driverDao.save(driverTwo);
        carDao.save(carTwo);
    }
}
