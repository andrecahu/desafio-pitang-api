package com.pitang.desafiopitangapi.repository;

import com.pitang.desafiopitangapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    boolean existsByLicensePlate(String licensePlate);
}
