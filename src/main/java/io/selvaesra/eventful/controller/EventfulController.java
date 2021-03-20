package io.selvaesra.eventful.controller;

import io.selvaesra.eventful.models.EventResponse;
import io.selvaesra.eventful.service.EventFulService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EventfulController {

    private final EventFulService eventfulService;

    public EventfulController(EventFulService eventfulService) {
        this.eventfulService = eventfulService;
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

    @ApiOperation(value = "Find All Events for given category and from and to date", notes = "${EventfulController.findEvents}")
    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<EventResponse> findEvents(@RequestParam("location") String location,
                                          @RequestParam("category") String category,
                                          @RequestParam("fromDateTime")String fromDateTime,
                                          @RequestParam("toDateTime")String toDateTime) {
        return eventfulService.getEventsFor(location,category,fromDateTime,toDateTime);
    }
}
