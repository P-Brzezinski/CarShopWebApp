package pl.altkom.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String routeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate plannedEndDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Column(length = 40)
    private String startAdress;

    @Column(length = 40)
    private String endAdress;

    @Transient
    private Long driverId;

    @Transient
    private Long carId;

    public Route() {
    }

    public Route(String routeName, LocalDate startDate, LocalDate plannedEndDate, LocalTime startTime, LocalTime endTime, String startAdress, String endAdress, Long driverId, Long carId) {
        this.routeName = routeName;
        this.startDate = startDate;
        this.plannedEndDate = plannedEndDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startAdress = startAdress;
        this.endAdress = endAdress;
        this.driverId = driverId;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getStartAdress() {
        return startAdress;
    }

    public void setStartAdress(String startAdress) {
        this.startAdress = startAdress;
    }

    public String getEndAdress() {
        return endAdress;
    }

    public void setEndAdress(String endAdress) {
        this.endAdress = endAdress;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
