package com.pitang.desafiopitangapi.controllers;

import com.pitang.desafiopitangapi.exceptions.BusinessException;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> register(@RequestBody Car car, HttpServletRequest request) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.register(car, request));
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllByLoggedUser(HttpServletRequest request) {
        return ResponseEntity.ok(carService.findAllByLoggedUser(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> findById(@PathVariable String id, HttpServletRequest request) throws BusinessException {
        return ResponseEntity.ok(carService.findByIdAndLoggedUser(id, request));
    }
}
