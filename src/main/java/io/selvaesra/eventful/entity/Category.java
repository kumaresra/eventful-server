package io.selvaesra.eventful.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(description = "Details about the Category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique ID for The Category", value = "${Category.id}")
    @Column(name="category_surrogate_id")
    private Integer id;
    @ApiModelProperty(notes = "The Category's name", value = "${Category.name}")
    @Column(name="category_name")
    private String name;
}
