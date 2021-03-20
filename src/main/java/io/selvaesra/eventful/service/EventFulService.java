package io.selvaesra.eventful.service;

import com.vividsolutions.jts.geom.Geometry;
import io.selvaesra.eventful.entity.Category;
import io.selvaesra.eventful.entity.Event;
import io.selvaesra.eventful.entity.Venue;
import io.selvaesra.eventful.models.EventResponse;
import io.selvaesra.eventful.models.weather.WeatherResponse;
import io.selvaesra.eventful.repositories.CategoryRepository;
import io.selvaesra.eventful.repositories.EventRepository;
import io.selvaesra.eventful.repositories.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EventFulService {

    public static final String UTC = "Etc/UTC";
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final VenueRepository venueRepository;
    private final WeatherService weatherService;

    public EventFulService(EventRepository eventRepository, CategoryRepository categoryRepository,
                           VenueRepository venueRepository, WeatherService weatherService) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.venueRepository = venueRepository;
        this.weatherService = weatherService;
    }


    public List<String> getAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        return StreamSupport.stream(categories.spliterator(),false)
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllLocations() {
        Iterable<Venue> venueList = venueRepository.findAll();
        return StreamSupport.stream(venueList.spliterator(),false)
                .map(Venue::getLocation).distinct().collect(Collectors.toList());
    }

    public List<EventResponse> getEventsFor(String location, String category,
                                            String fromDateTime, String toDateTime) {
        String fromDateTimeFormat = convertDateTimeToString(fromDateTime);
        String toDateTimeFormat = convertDateTimeToString(toDateTime);
        List<Event> events = eventRepository.findByLocationAndCategoryAndFromDateTime(
                location,category, fromDateTimeFormat,toDateTimeFormat);
        return events.stream().map(this::applyWeather).collect(Collectors.toList());
    }

    private String convertDateTimeToString(String fromDateTime) {
        ZonedDateTime fromDateTimeWithZone = Instant.ofEpochMilli(Long.parseLong(fromDateTime))
                .atZone(ZoneId.of(UTC));
        return fromDateTimeWithZone.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private EventResponse applyWeather(Event event) {
        Geometry geometryOfCoordinates = event.getVenue().getGeometryOfCoordinates();
        double latitude = geometryOfCoordinates.getCoordinate().x;
        double longitude = geometryOfCoordinates.getCoordinate().y;
        WeatherResponse weather = weatherService.getWeather(latitude, longitude);
        return new EventResponse(event, weather);
    }
}
