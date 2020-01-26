package pl.altkom.CarShop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.altkom.CarShop.model.Car;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private JdbcTemplate dao;
    public static String TABLE_NAME = "carsDB";

    @Override
    public void saveCar(Car car) {
        dao.update("INSERT INTO " + TABLE_NAME + "(carBrand, carModel, color, yearOfProduction, VIN) VALUES ( ?,?,?,?,? )",
                car.getCarBrand(),
                car.getCarModel(),
                car.getColor(),
                car.getYearOfProduction(),
                car.getVIN());
    }

    @Override
    public void editCar(Car car) {
             dao.update("UPDATE " + TABLE_NAME + " set carBrand=?, carModel=?, color=?, yearOfProduction=?, VIN=?",
                car.getCarBrand(),
                car.getCarModel(),
                car.getColor(),
                car.getYearOfProduction(),
                car.getVIN());
    }

    @Override
    public void deleteCar(int id) {
        final String DELETE_CAR_WITH_ID = String.format("DELETE FROM %s WHERE id=%d", TABLE_NAME, id);
        dao.execute(DELETE_CAR_WITH_ID);
    }

    @Override
    public List<Car> getCars() {
        final String FIND_ALL_CARS = String.format("SELECT * FROM %s", TABLE_NAME);
        return dao.query(FIND_ALL_CARS, new BeanPropertyRowMapper<>(Car.class));
    }
}
