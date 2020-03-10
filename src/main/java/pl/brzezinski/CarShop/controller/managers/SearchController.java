package pl.brzezinski.CarShop.controller.managers;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.model.Address;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private static final String KEY_FOR_TOMTOM = "U5AOrrRaKTNr4xW0fSqZt3Gyou9AJwiS";

    @GetMapping("/submitSearchForm")
    public String submitSearchForm(){
        return "/search";
    }

    @GetMapping("/search")
    public String getParamsForSearch(@RequestParam String city,
                                     @RequestParam String street,
                                     @RequestParam String country,
                                     final Model model) throws IOException {
        StringBuilder params = new StringBuilder();
        String[] paramsForSearch = {city, street, country};
        for (int i = 0; i < paramsForSearch.length; i++) {
            if (paramsForSearch[i] != null) {
                params.append(paramsForSearch[i]);
                if (i == 2){
                    break;
                }else {
                    params.append("%20");
                }
            }
        }
        List<Address> searchResult = executeQuery(params.toString());
        model.addAttribute("addressList", searchResult);
        return "searchResults";
    }

    public List<Address> executeQuery(String params) throws IOException {
        String url = createQuery(params);
        JSONObject json = readJsonFromUrl(url);

        int totalResults = json.getJSONObject("summary").getInt("numResults");
        List<Address> addressList = new ArrayList<>();

        for (int i = 0; i < totalResults ; i++) {
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

    private Long castObjectToLong(Object object){
        return Long.parseLong(object.toString());
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
