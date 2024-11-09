package com.pitang.desafiopitangapi.service;

import com.pitang.desafiopitangapi.exceptions.BusinessException;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public void register(Car car) {
        car.validate();
        if (carRepository.existsByLicensePlate(car.getLicensePlate())) {
            throw new BusinessException("License plate already exists", HttpStatus.BAD_REQUEST);
        }
        carRepository.save(car);
    }

    public void validateCarList(List<Car> list) {
        for (Car car : list) {
            car.validate();
        }
    }

}
