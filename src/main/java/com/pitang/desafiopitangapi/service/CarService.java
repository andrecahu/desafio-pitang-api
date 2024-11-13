package com.pitang.desafiopitangapi.service;

import com.pitang.desafiopitangapi.exceptions.BusinessException;
import com.pitang.desafiopitangapi.infra.security.TokenService;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.model.User;
import com.pitang.desafiopitangapi.repository.CarRepository;
import com.pitang.desafiopitangapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Car> findAllByLoggedUser(HttpServletRequest request) {
        User user = getUserByToken(request);
        return carRepository.findByUserId(user.getId());
    }

    public Car findByIdAndLoggedUser(String id, HttpServletRequest request) {
        User user = getUserByToken(request);
        return carRepository.findByIdAndUserId(id, user.getId()).orElseThrow(()-> new EntityNotFoundException("Car Not Found"));
    }

    public Car update(String id, Car car, HttpServletRequest request) {
        car.setId(id);
        car.validate();
        User user = getUserByToken(request);
        car.setUser(user);

        for(Car validationCar : user.getCars()){
            if(validationCar.getId().equals(id)){
                if (!validationCar.getLicensePlate().equals(car.getLicensePlate()) && carRepository.existsByLicensePlate(car.getLicensePlate())) {
                    throw new BusinessException("License plate already exists", HttpStatus.BAD_REQUEST);
                }
                return carRepository.save(car);
            }
        }
        throw new EntityNotFoundException("Car Not Found");
    }

    public void delete(String id, HttpServletRequest request){
        Car car = findByIdAndLoggedUser(id, request);
        carRepository.delete(car);
    }

    public void deleteByCar(Car car) {
        carRepository.delete(car);
    }

    public void validateCarList(List<Car> list) {
        for (Car car : list) {
            car.validate();
        }
    }

    public User getUserByToken(HttpServletRequest request){
        var token = this.tokenService.recoverToken(request);
        var login = tokenService.verifyToken(token);
        return userRepository.findByLogin(login).orElseThrow(() -> new BadCredentialsException("Invalid login"));

    }

}
