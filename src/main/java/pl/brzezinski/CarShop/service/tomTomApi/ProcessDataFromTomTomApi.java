package pl.brzezinski.CarShop.service.tomTomApi;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class ProcessDataFromTomTomApi {

    protected LocalTime setPlannedTimeInHours(Long seconds){
        Duration initialDuration = Duration.of(0, ChronoUnit.HOURS);
        Duration durationInSeconds = initialDuration.plusSeconds(seconds);
        int hours = durationInSeconds.toHoursPart();
        int minutes = durationInSeconds.toMinutesPart();
        int sec = durationInSeconds.toSecondsPart();
        return LocalTime.of(hours, minutes, sec);
    }

    protected Long toKilometers(Long lengthInMeters) {
        return lengthInMeters / 1000;
    }
}
