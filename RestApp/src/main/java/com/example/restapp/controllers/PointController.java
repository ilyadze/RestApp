package com.example.restapp.controllers;

import com.example.restapp.models.Point;
import com.example.restapp.models.Route;
import com.example.restapp.services.PointService;
import com.example.restapp.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("points")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public List<Point> getPoints() {
        return pointService.findAll();
    }

    @GetMapping("/{id}")
    public Point getPoint(@PathVariable("id")Integer id) {
        return pointService.findPoint(id);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePoint(@RequestBody @Valid Point point, BindingResult bindingResult) {
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

        pointService.deletePoint(point);
        return ResponseEntity.ok(HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Point point, BindingResult bindingResult) {
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
        pointService.addPoint(point);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateRoute(@PathVariable("id")Integer id,@RequestBody @Valid Point point, BindingResult bindingResult)  {
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
        pointService.update(id, point);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<PointErrorResponse> handleException(PointNotFoundException e) {
        PointErrorResponse responce = new PointErrorResponse(
                "Point with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PointErrorResponse> handleException(RouteException e) {
        PointErrorResponse responce = new PointErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
    }

}
