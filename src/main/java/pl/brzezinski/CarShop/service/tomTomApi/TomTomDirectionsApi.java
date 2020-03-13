package pl.brzezinski.CarShop.service.tomTomApi;

import com.github.sisyphsu.dateparser.DateParserUtils;
import org.json.JSONObject;
import pl.brzezinski.CarShop.model.Route;
import pl.brzezinski.CarShop.service.json.JsonService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TomTomDirectionsApi {

    private static final String KEY_FOR_TOMTOM = "U5AOrrRaKTNr4xW0fSqZt3Gyou9AJwiS";
    private ProcessDataFromTomTomApi process = new ProcessDataFromTomTomApi();
    private JsonService jsonService = new JsonService();

    public Route processRouteWithDataFromTomTom(Route route) throws IOException {
        String urlWithQueryForTomTom = createQueryForTomTom(route);
        JSONObject json = jsonService.readJsonFromUrl(urlWithQueryForTomTom);
        setEndDateTime(route, json);
        setDistanceInKilometers(route, json);
        setPlannedTimeInHours(route, json);
        route.setDone(false);
        return route;
    }

    private String createQueryForTomTom(Route route) {
        String startDateTime = parseStartDateTimeToString(route);
        final String query = String.format("https://api.tomtom.com/routing/1/calculateRoute/%s:%s/json?departAt=%s&key=%s",
                route.getStartAddress(),
                route.getEndAddress(),
                startDateTime,
                KEY_FOR_TOMTOM);
        return query;
    }

    private String parseStartDateTimeToString(Route route) {
        LocalDateTime startDateTime = route.getStartDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDateTime = startDateTime.format(formatter);
        return formattedDateTime;
    }

    private void setEndDateTime(Route route, JSONObject json) {
        String endDateTimeString = json.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").get("arrivalTime").toString();
        LocalDateTime plannedEndDateTime = DateParserUtils.parseDateTime(endDateTimeString);
        route.setEndDateTime(plannedEndDateTime);
    }

    private void setDistanceInKilometers(Route route, JSONObject json) {
        Long distanceInMeters = getDistanceInMeters(json);
        Long inKilometers = process.toKilometers(distanceInMeters);
        route.setDistance(inKilometers);
    }

    private Long getDistanceInMeters(JSONObject json) {
        Long lengthInMeters = castObjectToLong(json.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").get("lengthInMeters"));
        return lengthInMeters;
    }

    private void setPlannedTimeInHours(Route route, JSONObject json) {
        Long travelTimeInSeconds = getTravelTimeInSeconds(json);
        LocalTime travelTimeInHoursAndMInutes = process.setPlannedTimeInHours(travelTimeInSeconds);
        route.setTravelTime(travelTimeInHoursAndMInutes);
    }

    private Long getTravelTimeInSeconds(JSONObject json){
        Long travelTimeInSeconds = castObjectToLong(json.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").get("travelTimeInSeconds"));
        return travelTimeInSeconds;
    }

    private Long castObjectToLong(Object object){
        return Long.parseLong(object.toString());
    }
}