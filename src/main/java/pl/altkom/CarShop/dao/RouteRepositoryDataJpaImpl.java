package pl.altkom.CarShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.altkom.CarShop.model.Route;

public interface RouteRepositoryDataJpaImpl extends JpaRepository<Route, Long> {
}
