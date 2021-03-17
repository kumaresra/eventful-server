package io.selvaesra.eventful.models.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Weather {
    private String main;
}
