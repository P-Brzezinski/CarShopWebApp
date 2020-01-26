package pl.altkom.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.altkom.CarShop.model.Car;

public interface CarRepositoryDataJpaImpl extends JpaRepository<Car, Long > {

}
