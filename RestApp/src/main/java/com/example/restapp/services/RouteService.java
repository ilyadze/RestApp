package com.example.restapp.services;

import com.example.restapp.models.Route;
import com.example.restapp.repositories.RouteRepository;
import com.example.restapp.util.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route findRoute(int id) {
        return routeRepository.findById(id).orElseThrow(RouteNotFoundException::new);
    }

    public void update(int id, Route route) {
        route.setId(id);
        routeRepository.save(route);
    }

    @Transactional
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Transactional
    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }

    @Transactional
    public void deleteRouteById(int id) {
        routeRepository.deleteById(id);
    }
}
