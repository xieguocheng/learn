package com.learn.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
public class School {
    @Id
    private Integer id;

    private String name;

    private String place;

    private String type;

    private String properties;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return properties
     */
    public String getProperties() {
        return properties;
    }

    /**
     * @param properties
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }
}