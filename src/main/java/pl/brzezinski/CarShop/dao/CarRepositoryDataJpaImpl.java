package pl.brzezinski.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.brzezinski.CarShop.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarRepositoryDataJpaImpl extends JpaRepository<Car, Long > {

    List<Car> getAllByCarBrand(String carBrand);
    List<Car> getAllByYearOfProductionBefore(LocalDate date);
    List<Car> getAllByCarBrandIsNotAndCarBrandIsNotAndCarBrandIsNotAndCarBrandIsNot(String brand1, String brand2, String brand3, String brand4);
    List<Car> getAllByColorAndCarBrand(String color, String carBrand);
    List<Car> getAllByCarBrandOrderByYearOfProductionDesc(String carBrand);
    List<Car> getAllByCarBrandAndColorIsNot(String brand, String color);
    List<Car> getAllByCarBrandIsAndCarModelIsAndColorIs(String carBrand, String carModel, String color);

}
