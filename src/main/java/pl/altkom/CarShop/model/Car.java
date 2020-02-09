package pl.altkom.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "carsDB")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20, message = "{first.inCorrectCarBrandLength}")
    @Column(length = 20, name = "car_brand")
    private String carBrand;

    @NotNull
    @Size(min = 2, max = 20, message = "{first.inCorrectCarModelLength}")
    @Column(length = 20, name = "car_model")
    private String carModel;

    @NotNull
    @Size(min = 2, max = 20, message = "{first.icCorrectColorLength}")
    @Column(length = 20, name = "car_color")
    private String color;

    @NotNull(message = "{first.noDate}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate yearOfProduction;

    @NotNull
    @Size(min = 17, max = 17, message = "{first.inCorrectVinLength}")
    @Column(length = 17, name = "VIN_number")
    private String VIN;

    @OneToMany
    @JoinColumn(name = "car_id") //tabela route
    private List<Route> routes = new ArrayList<>(0);

    public Car() {
    }

    public Car(String carBrand, String carModel, String color, LocalDate yearOfProduction, String VIN) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.color = color;
        this.yearOfProduction = yearOfProduction;
        this.VIN = VIN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(LocalDate yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", color='" + color + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", VIN='" + VIN + '\'' +
                '}';
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }
}
