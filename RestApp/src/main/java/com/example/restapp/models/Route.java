package com.example.restapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Route")
public class Route {

    @Id
    @Column(name = "route_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "distance")
    private double distance;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
    private List<Point> points;

    public Route() {}


    public Route(int id, String name, double distance, String description) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.description = description;
    }

    public Route(String name, double distance, String description) {
        this.name = name;
        this.distance = distance;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", description='" + description + '\'' +
                '}';
    }
}
