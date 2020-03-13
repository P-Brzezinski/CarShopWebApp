package pl.brzezinski.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.brzezinski.CarShop.model.Driver;

public interface DriverRepositoryDataJpaImpl extends JpaRepository<Driver, Long> {

    Driver findDriverByLastName(String lastName);

    Driver findDriverByFirstName(String firstName);
}
