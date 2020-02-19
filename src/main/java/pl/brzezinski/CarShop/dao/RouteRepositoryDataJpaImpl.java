package pl.brzezinski.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.brzezinski.CarShop.model.Route;

public interface RouteRepositoryDataJpaImpl extends JpaRepository<Route, Long> {
}
