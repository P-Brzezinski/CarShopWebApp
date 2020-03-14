package pl.brzezinski.CarShop.model;

public class Address {

    private String type;
    private String country;
    private String street;
    private String lat;
    private String lon;

    public Address() {
    }

    public Address(String type, String country, String street, String lat, String lon) {
        this.type = type;
        this.country = country;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Address{" +
                "type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", steet='" + street + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
