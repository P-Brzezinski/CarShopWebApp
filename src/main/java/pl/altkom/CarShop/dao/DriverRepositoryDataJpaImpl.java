package pl.altkom.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.altkom.CarShop.model.Driver;

import java.util.List;

public interface DriverRepositoryDataJpaImpl extends JpaRepository<Driver, Long> {

}
