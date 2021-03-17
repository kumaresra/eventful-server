package io.selvaesra.eventful.controller;

import io.selvaesra.eventful.entity.Event;
import io.selvaesra.eventful.models.EventResponse;
import io.selvaesra.eventful.service.EventFulService;
import io.selvaesra.eventful.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EventfulController {

    private final EventFulService eventfulService;
    private final WeatherService weatherService;

    public EventfulController(EventFulService eventfulService, WeatherService weatherService) {
        this.eventfulService = eventfulService;
        this.weatherService = weatherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public String ping() {
        return "Ping Success";
    }
    @ApiOperation(value = "Find All Eventful Categories", notes = "${EventfulController.getAllCategories}")
    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    public List<String> getAllCategories() {
        return eventfulService.getAllCategories();
    }

    @ApiOperation(value = "Find All Eventful Locations", notes = "${EventfulController.getAllLocations}")
    @RequestMapping(method = RequestMethod.GET, value = "/locations")
    public List<String> getAllLocations() {
        return eventfulService.getAllLocations();
    }

    @ApiOperation(value = "Find All Events for given category and from date", notes = "${EventfulController.findEvents}")
    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<EventResponse> findEvents(@RequestParam("location") String location,
                                          @RequestParam("category") String category,
                                          @RequestParam("fromDateTime")String fromDateTime) {
        return eventfulService.getEventsFor(location,category,fromDateTime);
    }




    private List<Event> getEvents() {
        List<Event> events = new ArrayList();
        Event event = new Event();
        event.setId(1000);
        event.setName("Magic Mike Live at the Hippodrome Casino");
        event.setShortDescription("Soak up the atmosphere in this sensual West End dance show.");
        event.setFromDatetime(LocalDateTime.now());
        event.setPhotoUrl("https://picsum.photos/200/300");
        events.add(event);
        return events;
    }
}
