package com.hobbyproject.carrentalservice.repository;

import com.hobbyproject.carrentalservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CarRepository</h2>
 * @date 2024-04-02
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
