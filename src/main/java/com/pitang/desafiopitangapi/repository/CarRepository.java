package com.pitang.desafiopitangapi.repository;

import com.pitang.desafiopitangapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the {@link Car} entity.
 * Extends {@link JpaRepository} to provide standard JPA functionality.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    /**
     * Checks if a car with the given license plate already exists in the database.
     *
     * @param licensePlate The license plate of the car to be checked.
     * @return {@code true} if a car with the given license plate exists, {@code false} otherwise.
     */
    boolean existsByLicensePlate(String licensePlate);

    /**
     * Retrieves a list of cars associated with the user specified by their ID.
     *
     * @param id The ID of the user whose cars are to be retrieved.
     * @return A list of cars associated with the specified user ID.
     */
    List<Car> findByUserId(String id);

    /**
     * Retrieves a car by its ID and the ID of the user who owns it.
     *
     * @param carId The ID of the car to be retrieved.
     * @param userId The ID of the user who owns the car.
     * @return An {@link Optional} containing the car if found, or an empty {@link Optional} if not found.
     */
    Optional<Car> findByIdAndUserId(String carId, String userId);
}
