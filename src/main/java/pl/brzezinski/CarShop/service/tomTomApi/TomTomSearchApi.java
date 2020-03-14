package pl.brzezinski.CarShop.service.tomTomApi;

import org.json.JSONObject;
import pl.brzezinski.CarShop.model.Address;
import pl.brzezinski.CarShop.service.json.JsonService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TomTomSearchApi {

    private static final String KEY_FOR_TOMTOM = "U5AOrrRaKTNr4xW0fSqZt3Gyou9AJwiS";
    private JsonService jsonService = new JsonService();

    public List<Address> executeQuery(String params) throws IOException {
        String url = createSearchQuery(params);
        JSONObject json = jsonService.readJsonFromUrl(url);

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
        }
        return addressList;
    }

    private String createSearchQuery(String params) {
        String url = String.format("https://api.tomtom.com/search/2/geocode/%s.json?extendedPostalCodesFor=Addr&key=%s",
                params,
                KEY_FOR_TOMTOM);
        return url;
    }
}
