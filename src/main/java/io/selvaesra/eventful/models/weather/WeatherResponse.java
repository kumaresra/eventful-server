package io.selvaesra.eventful.models.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(description = "Details about the Weather")
public class WeatherResponse {
    private Main main;
    private List<Weather> weather;

}
