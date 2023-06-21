package com.example.restapp.controllers;

import com.example.restapp.models.Route;
import com.example.restapp.services.RouteService;
import com.example.restapp.util.RouteErrorResponse;
import com.example.restapp.util.RouteException;
import com.example.restapp.util.RouteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable("id")Integer id) {
        return routeService.findRoute(id);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRoute(@RequestBody @Valid Route route, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors =  bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RouteException((errMsg.toString()));
        }

        routeService.deleteRoute(route);
        return ResponseEntity.ok(HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Route route, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors =  bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RouteException((errMsg.toString()));
        }
        routeService.addRoute(route);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateRoute(@PathVariable("id")Integer id,@RequestBody @Valid Route route, BindingResult bindingResult)  {
        if(bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors =  bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RouteException((errMsg.toString()));
        }
        routeService.update(id, route);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<RouteErrorResponse> handleException(RouteNotFoundException e) {
        RouteErrorResponse responce = new RouteErrorResponse(
                "Route with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<RouteErrorResponse> handleException(RouteException e) {
        RouteErrorResponse responce = new RouteErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
    }

}
