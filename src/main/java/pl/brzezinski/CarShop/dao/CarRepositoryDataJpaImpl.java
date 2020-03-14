package pl.brzezinski.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.brzezinski.CarShop.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarRepositoryDataJpaImpl extends JpaRepository<Car, Long > {
}
