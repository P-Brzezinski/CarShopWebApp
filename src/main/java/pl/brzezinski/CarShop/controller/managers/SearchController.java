package pl.brzezinski.CarShop.controller.managers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.dao.CarRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.DriverRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.dao.RouteRepositoryDataJpaImpl;
import pl.brzezinski.CarShop.model.Address;
import pl.brzezinski.CarShop.model.Car;
import pl.brzezinski.CarShop.model.Driver;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.service.tomTomApi.TomTomDirectionsApi;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private static final String KEY_FOR_TOMTOM = "U5AOrrRaKTNr4xW0fSqZt3Gyou9AJwiS";

    @Autowired
    DriverRepositoryDataJpaImpl driverDao;

    @Autowired
    CarRepositoryDataJpaImpl carDao;

    @Autowired
    RouteRepositoryDataJpaImpl routeDao;

    TomTomDirectionsApi tomTomDirectionsApi = new TomTomDirectionsApi();

    Route route = new Route();

    @GetMapping("/searchForStartAddress")
    public String searchForStartAddress() {
        return "searchStartAddressForm";
    }

    @GetMapping("/searchForEndAddress")
    public String searchForEndAddress(Route route) {
        this.route.setRouteName(route.getRouteName());
        this.route.setStartDateTime(route.getStartDateTime());
        this.route.setStartAddress(route.getStartAddress());
        this.route.setDriverId(route.getDriverId());
        this.route.setCarId(route.getCarId());
        System.out.println("1111111111111111111111");
        System.out.println(route.toString());
        return "searchEndAddressForm";
    }

    @GetMapping("/assignEndAddress")
    public String assignEndAddress(@RequestParam(name = "lat") String lat,
                                   @RequestParam(name = "lon") String lon) throws IOException {
        this.route.setEndAddress(lat + "," + lon);

        System.out.println("222222222222222222222222222222");
        System.out.println(route.toString());

        Long driverId = route.getDriverId();// tylko dla formularza
        Driver driver = driverDao.getOne(driverId);

        Long carId = route.getCarId();
        Car car = carDao.getOne(carId);

        // 2. Dla pobranego kierowcy dodajemy trase i do samochodu trase
        driver.addRoute(route);
        car.addRoute(route);

        System.out.println(route.toString());
        tomTomDirectionsApi.processRouteWithDataFromTomTom(this.route);

        // 3. zapisujemy trase, kierowce, pojazd

        routeDao.save(route); // insert do tabeli Route z driver_id = null
        driverDao.save(driver); // update na tabeli Route z wpisaniem driver_id
        carDao.save(car); // update na tabeli Route z wpisaniem car_id
        System.out.println(route.toString());
        return "redirect:/";
    }

    @GetMapping("/searchForPossibleStartResults")
    public String searchForPossibleStartResults(@RequestParam String city,
                                                @RequestParam String street,
                                                @RequestParam String country,
                                                final Model model) throws IOException {
        StringBuilder params = new StringBuilder();
        String[] paramsForSearch = {city, street, country};
        for (int i = 0; i < paramsForSearch.length; i++) {
            if (paramsForSearch[i] != null) {
                params.append(paramsForSearch[i]);
                if (i == 2) {
                    break;
                } else {
                    params.append("%20");
                }
            }
        }
        List<Address> searchResult = executeQuery(params.toString());
        model.addAttribute("addressList", searchResult);
        return "searchStartAddressForm";
    }

    @GetMapping("/searchForPossibleEndResults")
    public String searchForPossibleEndResults(@RequestParam String city,
                                              @RequestParam String street,
                                              @RequestParam String country,
                                              final Model model) throws IOException {
        StringBuilder params = new StringBuilder();
        String[] paramsForSearch = {city, street, country};
        for (int i = 0; i < paramsForSearch.length; i++) {
            if (paramsForSearch[i] != null) {
                params.append(paramsForSearch[i]);
                if (i == 2) {
                    break;
                } else {
                    params.append("%20");
                }
            }
        }
        List<Address> searchResult = executeQuery(params.toString());
        model.addAttribute("addressList", searchResult);
        return "searchEndAddressForm";
    }

    @GetMapping("/createRouteWithStartAddress")
    public String createRouteWithStartAddress(@RequestParam(name = "lat") String lat,
                                              @RequestParam(name = "lon") String lon,
                                              @RequestParam(name = "street") String street,
                                              @RequestParam(name = "country") String country,
                                              @RequestParam(name = "type") String type,
                                              Model model) {
        Address startAddress = new Address(type, country, street, lat, lon);
        route.setStartAddress(lat + "," + lon);
        System.out.println(">>>>>>>>>>>>>>>>>>>" + route.getStartAddress());
        model.addAttribute("startAddress", startAddress);
        model.addAttribute(route);
        List<Driver> allDrivers = driverDao.findAll();
        model.addAttribute("allDrivers", allDrivers);
        List<Car> allCars = carDao.findAll();
        model.addAttribute("allCars", allCars);
        return "enterDetails";
    }

    public List<Address> executeQuery(String params) throws IOException {
        String url = createQuery(params);
        JSONObject json = readJsonFromUrl(url);

        int totalResults = json.getJSONObject("summary").getInt("numResults");
        List<Address> addressList = new ArrayList<>();

        for (int i = 0; i < totalResults; i++) {
            Address address = new Address();
            address.setType(json.getJSONArray("results").getJSONObject(i).getString("type"));
            address.setCountry(json.getJSONArray("results").getJSONObject(i).getJSONObject("address").getString("country"));
            address.setStreet(json.getJSONArray("results").getJSONObject(i).getJSONObject("address").getString("freeformAddress"));
            address.setLat(String.valueOf(json.getJSONArray("results").getJSONObject(i).getJSONObject("position").getFloat("lat")));
            address.setLon(String.valueOf(json.getJSONArray("results").getJSONObject(i).getJSONObject("position").getFloat("lon")));
            addressList.add(address);
            System.out.println(address.toString());
        }

        System.out.println(totalResults);

        System.out.println(json);

        return addressList;
    }

    private String createQuery(String params) {
        String url = String.format("https://api.tomtom.com/search/2/geocode/%s.json?extendedPostalCodesFor=Addr&key=%s",
                params,
                KEY_FOR_TOMTOM);
        System.out.println(url);
        return url;
    }

    private JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
