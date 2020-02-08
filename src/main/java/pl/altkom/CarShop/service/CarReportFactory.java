package pl.altkom.CarShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altkom.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.altkom.CarShop.model.Car;
import pl.altkom.CarShop.model.CarReport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarReportFactory {

    @Autowired
    private CarRepositoryDataJpaImpl dao;

    public CarReport createReport(final Integer reportType) {

        CarReport result = null;

        switch (reportType) {
            case 1: {
                result = new CarReport("All Ferrari in database", dao.getAllByCarBrand("Ferrari"));
                break;
            }
            case 2: {
                result = new CarReport("Cars older than 10 years", dao.getAllByYearOfProductionBefore(LocalDate.of(2010, 1, 1)));
                break;
            }
            case 3: {
                result = new CarReport("Car Brands other than Volkswagen, Audi, Skoda, Seat",
                        dao.getAllByCarBrandIsNotAndCarBrandIsNotAndCarBrandIsNotAndCarBrandIsNot("Volkswagen", "Audi", "Skoda", "Seat"));
                break;
            }
            case 4: {
                result = new CarReport("All Ferrari with red color", dao.getAllByColorAndCarBrand("red", "Ferrari"));
                break;
            }
            case 5: {
                result = new CarReport("Ford cars sorted from newest to oldest", dao.getAllByCarBrandOrderByYearOfProductionDesc("Ford"));
                break;
            }
            case 6: {
                result = new CarReport("Pegueot with color other than grey", dao.getAllByCarBrandAndColorIsNot("Peugot", "grey"));
                break;
            }
        }
        return result;
    }

    public CarReport createReportByUserInput(String carBrand, String carModel, String carColor){
        CarReport report = new CarReport("Report by User Input", dao.getAllByCarBrandIsAndCarModelIsAndColorIs(carBrand, carModel, carColor));
        return report;
    }
}
