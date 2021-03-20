package io.selvaesra.eventful.service;

import io.selvaesra.eventful.configuration.RestTemplateLoadBalanced;
import io.selvaesra.eventful.models.weather.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    private final RestTemplateLoadBalanced restTemplate;

    public WeatherService(RestTemplateLoadBalanced restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(double lattitude, double longitude){
        final String fullUrl = url+"?lat=" + lattitude + "&lon=" + longitude +
                "&APPID=" + apiKey + "&units=metric";
        ResponseEntity<WeatherResponse> response = restTemplate.getTemplate()
                .getForEntity(fullUrl, WeatherResponse.class);
        return response.getBody();
    }

}
