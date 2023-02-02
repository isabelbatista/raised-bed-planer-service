package org.belgaia.permaculture.raisedbedplanerservice.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "permaculture-designer")
public class RaisedBed {

    private static final Integer FIELD_COUNT_X = 6;
    private static final Integer FIELD_COUNT_Y = 4;

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer dimensionX;

    @Field(type = FieldType.Integer)
    private Integer dimensionY;

    @Field(type = FieldType.Integer)
    private Integer defaultFieldCountX;

    @Field(type = FieldType.Integer)
    private Integer defaultFieldCountY;

    @Field(type = FieldType.Nested)
    private List<Planting> plantings;

    public RaisedBed() {
        this.plantings = new ArrayList<Planting>();
    }

    public RaisedBed(String name, Integer dimensionX, Integer dimensionY) {
        this.name = name;
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.defaultFieldCountX = FIELD_COUNT_X;
        this.defaultFieldCountY = FIELD_COUNT_Y;
        this.plantings = new ArrayList<Planting>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(Integer dimensionX) {
        this.dimensionX = dimensionX;
    }

    public Integer getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(Integer dimensionY) {
        this.dimensionY = dimensionY;
    }

    public Integer getDefaultFieldCountX() {
        return defaultFieldCountX;
    }

    public void setDefaultFieldCountX(Integer defaultFieldCountX) {
        this.defaultFieldCountX = defaultFieldCountX;
    }

    public Integer getDefaultFieldCountY() {
        return defaultFieldCountY;
    }

    public void setDefaultFieldCountY(Integer defaultFieldCountY) {
        this.defaultFieldCountY = defaultFieldCountY;
    }

    public List<Planting> getPlantings() {
        return plantings;
    }

    public void setPlantings(List<Planting> plantings) {
        this.plantings = plantings;
    }
}
