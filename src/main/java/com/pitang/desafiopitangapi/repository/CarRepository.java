package com.pitang.desafiopitangapi.repository;

import com.pitang.desafiopitangapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    boolean existsByLicensePlate(String licensePlate);

    List<Car> findByUserId(String id);

    Optional<Car> findByIdAndUserId(String carId, String userId);
}
