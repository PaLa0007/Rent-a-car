package com.course_project01_rent_a_car.rent_a_car.controllers;

import com.course_project01_rent_a_car.rent_a_car.entities.*;
import  com.course_project01_rent_a_car.rent_a_car.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        carService.createCar(car);
        return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<Car>> getCarsByLocation(@PathVariable String location) {
        List<Car> cars = carService.getCarsByLocation(location);
        return ResponseEntity.ok(cars);
    }

    // Get all cars
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    // Update car
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car car = carService.updateCar(id, updatedCar);
        return new ResponseEntity<>("Car updated successfully", HttpStatus.OK);
    }

    // Delete car
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
    }
}

