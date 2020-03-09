package pl.brzezinski.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.brzezinski.CarShop.model.enums.Color;
import pl.brzezinski.CarShop.model.validators.CanNotBeEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "carsDB")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "{carFormError.incorrectCarBrandLength}")
    @Column(length = 20, name = "car_brand")
    private String carBrand;

    @Size(min = 2, max = 20, message = "{carFormError.incorrectCarModelLength}")
    @Column(length = 20, name = "car_model")
    private String carModel;

    @CanNotBeEmpty
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "car_color")
    private Color color;

    @Positive(message = "{carFormError.mustBePositive}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Integer yearOfProduction;

    @Size(min = 17, max = 17, message = "{carFormError.incorrectVinLength}")
    @Column(length = 17, name = "VIN_number")
    private String VIN;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id") //tabela route
    private List<Route> routes = new ArrayList<>(0);

    public Car() {
    }

    public Car(String carBrand, String carModel, Color color, Integer yearOfProduction, String VIN) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public List<Route> getRoutes() {
        return routes;
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
