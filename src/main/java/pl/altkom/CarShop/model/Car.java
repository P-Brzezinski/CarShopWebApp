package pl.altkom.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ResponseBody
public class Car {

    private int id;
    @NotNull
    @Size(min = 2, max = 20, message = "{first.inCorrectCarBrandLength}")
    private String carBrand;
    @NotNull
    @Size(min = 2, max = 20, message = "{first.inCorrectCarModelLength}")
    private String carModel;
    @NotNull
    @Size(min = 2, max = 20, message = "{first.icCorrectColorLength}")
    private String color;
    @NotNull(message = "{first.noDate}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfProduction;
    @NotNull
    @Size(min = 17, max = 17, message = "{first.inCorrectVinLength}")
    private String VIN;

    public Car() {
    }

    public Car(String carBrand, String carModel, String color, LocalDate yearOfProduction, String VIN) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.color = color;
        this.yearOfProduction = yearOfProduction;
        this.VIN = VIN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
