package io.selvaesra.eventful.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.selvaesra.eventful.entity.Event;
import io.selvaesra.eventful.models.weather.WeatherResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(description = "Details about the Event")
public class EventResponse{

    private Integer id;
    private String name;
    private String shortDescription;
    private LocalDateTime fromDatetime;
    private LocalDateTime toDatetime;
    private String photoUrl;
    private String location;
    private String category;
    private String temperature;
    private String weather;

    public EventResponse(Event event, WeatherResponse weather) {
        this.id = event.getId();
        this.name = event.getName();
        this.shortDescription = event.getShortDescription();
        this.fromDatetime = event.getFromDatetime();
        this.toDatetime = event.getToDatetime();
        this.photoUrl = event.getPhotoUrl();
        this.location = event.getVenue().getLocation();
        this.category = event.getCategory().getName();
        this.temperature = weather.getMain().getTemp();
        this.weather = weather.getWeather().get(0).getMain();
    }
}
