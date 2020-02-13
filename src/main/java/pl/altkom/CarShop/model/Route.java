package pl.altkom.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalTime plannedEndTime;

    @Column(length = 40)
    private String startAddress;

    @Column(length = 40)
    private String endAddress;

//    @Transient
    @Column(length = 10, name = "dID")
    private Long driverId;

//    @Transient
    @Column(length = 10, name = "cID")
    private Long carId;

    public Route() {
    }

    public Route(String routeName, LocalDate startDate, LocalDate plannedEndDate, LocalTime startTime, LocalTime plannedEndTime, String startAddress, String endAddress, Long driverId, Long carId) {
        this.routeName = routeName;
        this.startDate = startDate;
        this.plannedEndDate = plannedEndDate;
        this.startTime = startTime;
        this.plannedEndTime = plannedEndTime;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
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

    public LocalTime getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(LocalTime endTime) {
        this.plannedEndTime = endTime;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAdress) {
        this.startAddress = startAdress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAdress) {
        this.endAddress = endAdress;
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
