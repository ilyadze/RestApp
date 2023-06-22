package com.example.restapp.dto;

import com.example.restapp.models.Route;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class PointDTO {
    private String name;
    private double latitude;
    private double longitude;
    private Integer routeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
