package io.selvaesra.eventful.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(description = "Details about the Event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique ID for The Event", value = "${Event.id}")
    @Column(name="event_surrogate_id")
    private Integer id;
    @ApiModelProperty(notes = "The Event's name", value = "${Event.name}")
    @Column(name="event_name")
    private String name;
    @ApiModelProperty(notes = "The Event's Short Description",value = "${Event.shortDescription}")
    @Column(name="short_description")
    private String shortDescription;
    @ApiModelProperty(notes = "The Event's From Date time",value = "${Event.fromDatetime}")
    @Column(name="from_datetime")
    private LocalDateTime fromDatetime;
    @ApiModelProperty(notes = "The Event's to Datetime",value = "${Event.toDatetime}")
    @Column(name="to_datetime")
    private LocalDateTime toDatetime;
    @ApiModelProperty(notes = "The Event's photo_url",value = "${Event.photo_url}")
    @Column(name="photo_url")
    private String photoUrl;
    @OneToOne(optional = false)
    @JoinColumn(name = "venue_surrogate_id", updatable = false, insertable = false)
    private Venue venue;
    @OneToOne(optional = false)
    @JoinColumn(name = "category_surrogate_id", updatable = false, insertable = false)
    private Category category;

}
