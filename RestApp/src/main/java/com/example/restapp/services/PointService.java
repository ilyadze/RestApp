package com.example.restapp.services;

import com.example.restapp.models.Point;
import com.example.restapp.models.Route;
import com.example.restapp.repositories.PointRepository;
import com.example.restapp.util.PointNotFoundException;
import com.example.restapp.util.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    public Point findRoute(int id) {
        return pointRepository.findById(id).orElseThrow(PointNotFoundException::new);
    }

    public void update(int id, Point point) {
        point.setId(id);
        pointRepository.save(point);
    }

    @Transactional
    public void addRoute(Point point) {
        pointRepository.save(point);
    }

    @Transactional
    public void deleteRoute(Point point) {
        pointRepository.delete(point);
    }

    @Transactional
    public void deleteRouteById(int id) {
        pointRepository.deleteById(id);
    }
}
