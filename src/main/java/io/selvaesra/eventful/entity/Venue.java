package io.selvaesra.eventful.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "VENUE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(description = "Details about the Venue")
public class Venue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique ID for The Venue", value = "${Venue.id}")
    @Column(name="venue_surrogate_id")
    private Integer id;
    @ApiModelProperty(notes = "The Venue's name", value = "${Venue.name}")
    @Column(name="venue_name")
    private String name;
    @ApiModelProperty(notes = "The Venue's location", value = "${Venue.location}")
    private String location;
    @ApiModelProperty(notes = "The Venue's coordinate", value = "${Venue.coordinate}")
    @Formula("ST_ASTEXT(coordinate)")
    private String coordinate;

    public Geometry getGeometryOfCoordinates() {
        try {
            WKTReader reader = new WKTReader();
            return reader.read(coordinate);
        } catch (ParseException ex) {
            return null;
        }
    }
}
