package io.selvaesra.eventful.service;

import io.selvaesra.eventful.models.weather.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Value("${weather.apikey}")
    private String apiKey;

    @Value("${weather.url}")
    private String url;

    public WeatherResponse getWeather(double lattitude, double longitude){
        final String fullUrl = url+"?lat=" + lattitude + "&lon=" + longitude +
                "&APPID=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherResponse> response = restTemplate
                .getForEntity(fullUrl, WeatherResponse.class);
        return response.getBody();
    }

}
