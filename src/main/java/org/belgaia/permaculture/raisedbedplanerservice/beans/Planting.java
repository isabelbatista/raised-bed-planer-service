package org.belgaia.permaculture.raisedbedplanerservice.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "permaculture-designer")
public class Planting {

    // TODO: better to use ElasticSearch generated ID, currently UUID
    private String id;

    @Field(type = FieldType.Text)
    private String status;

    @Field(type = FieldType.Integer)
    private Integer year;

    @Field(type = FieldType.Text)
    private java.lang.String season;

    @Field(type = FieldType.Integer)
    private Integer fieldCountX;

    @Field(type = FieldType.Integer)
    private Integer fieldCountY;

    private List<Plant> plants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public java.lang.String getSeason() {
        return season;
    }

    public void setSeason(java.lang.String season) {
        this.season = season;
    }

    public Integer getFieldCountX() {
        return fieldCountX;
    }

    public void setFieldCountX(Integer fieldCountX) {
        this.fieldCountX = fieldCountX;
    }

    public Integer getFieldCountY() {
        return fieldCountY;
    }

    public void setFieldCountY(Integer fieldCountY) {
        this.fieldCountY = fieldCountY;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
