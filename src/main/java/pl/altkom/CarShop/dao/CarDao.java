package pl.altkom.CarShop.dao;

import pl.altkom.CarShop.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(Car car);

    void editCar(Car car);

    void deleteCar(int id);

    List<Car> getCars();
}
