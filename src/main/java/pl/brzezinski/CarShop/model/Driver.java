package pl.brzezinski.CarShop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "{driverFormError.incorrectFirstNameLength}")
    @Column(length = 20, name = "first_name")
    private String firstName;

    @Size(min = 2, max = 20, message = "{driverFormError.incorrectLastNameLength}")
    @Column(length = 40, name = "last_name")
    private String lastName;

    @Column(name = "distance_taken")
    private Long distanceTaken;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "driver_id") // w tabeli route !
    private List<Route> routes = new ArrayList<>(0);

    public void addRoute(final Route route){
        this.routes.add(route);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Driver() {
    }

    public Driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDistanceTaken() {
        return distanceTaken;
    }

    public void setDistanceTaken(Long distanceTaken) {
        this.distanceTaken = distanceTaken;
    }
}
