package com.pitang.desafiopitangapi.service;

import com.pitang.desafiopitangapi.exceptions.BusinessException;
import com.pitang.desafiopitangapi.infra.security.TokenService;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.model.User;
import com.pitang.desafiopitangapi.repository.CarRepository;
import com.pitang.desafiopitangapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    public Car register(Car car, HttpServletRequest request) {
        car.validate();
        if (request != null) {
            User user = getUserByToken(request);
            car.setUser(user);
        }
        if (carRepository.existsByLicensePlate(car.getLicensePlate())) {
            throw new BusinessException("License plate already exists", HttpStatus.BAD_REQUEST);
        }
        return carRepository.save(car);
    }

    public void validateCarList(List<Car> list) {
        for (Car car : list) {
            car.validate();
        }
    }

    public void deleteByCar(Car car) {
        carRepository.delete(car);
    }

    public User getUserByToken(HttpServletRequest request){
        var token = this.tokenService.recoverToken(request);
        var login = tokenService.verifyToken(token);
        return userRepository.findByLogin(login).orElseThrow(() -> new BadCredentialsException("Invalid login"));

    }

}
