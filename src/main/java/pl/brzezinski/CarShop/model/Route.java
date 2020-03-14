package pl.brzezinski.CarShop.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.brzezinski.CarShop.model.validators.CanNotBeEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 40, message = "{routeFormError.incorrectRouteNameLength}")
    @Column(length = 40, name = "route_name")
    private String routeName;

    @CanNotBeEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @FutureOrPresent(message = "This date can not be in the past")
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "planned_end_date_time")
    private LocalDateTime endDateTime;

    @Size(min = 1, max = 40, message = "{routeFormError.incorrectStartAddressLength}")
    @Column(length = 40, name = "start_address")
    private String startAddress;

    @Size(min = 1, max = 40, message = "{routeFormError.incorrectEndAddressLength}")
    @Column(length = 40, name = "end_address")
    private String endAddress;

    @CanNotBeEmpty
    @Column(length = 1, name = "driver_ID")
    private Long driverId;

    @CanNotBeEmpty
    @Column(length = 1, name = "car_ID")
    private Long carId;

    @Column(name = "distance_in_meters")
    private Long distance;

    @Column(name = "travelTimeInSeconds")
    private LocalTime travelTime;

    @Column(name = "is_done")
    private boolean isDone;

    public Route() {
    }

    public Route(String routeName, LocalDateTime startDateTime, String startAddress, String endAddress, Long driverId, Long carId) {
        this.routeName = routeName;
        this.startDateTime = startDateTime;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime plannedEndDateTime) {
        this.endDateTime = plannedEndDateTime;
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

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTimeInSeconds) {
        this.travelTime = travelTimeInSeconds;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", driverId=" + driverId +
                ", carId=" + carId +
                ", distance=" + distance +
                ", travelTimeInSeconds=" + travelTime +
                ", isDone=" + isDone +
                '}';
    }
}
