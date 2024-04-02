package com.hobbyproject.carrentalservice.service;

import com.hobbyproject.carrentalservice.api.dto.CarDto;
import com.hobbyproject.carrentalservice.entity.Car;
import com.hobbyproject.carrentalservice.entity.Status;
import com.hobbyproject.carrentalservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CarService</h2>
 * @date 2024-04-02
 */

@Service
public class CarService {

    private CarRepository carRepo;

    public CarService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Car createCar(CarDto carDto) {
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setRegNum(carDto.getRegNum());
        car.setStatus(Status.AVAILABLE);
        car.setOwner(null);
        car.setAssociationDate(null);
        return carRepo.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepo.findById(id);
    }

    public void deleteCarById(Long id) {
        Optional<Car> opCar = getCarById(id);
        if (opCar.isPresent()) {
            Car car = opCar.get();
            if (car.getStatus() == Status.AVAILABLE) {
                carRepo.delete(opCar.get());
            } else {
                throw new IllegalArgumentException("Car is Associated with a user, so cannot be deleted");
            }
        } else {
            throw new NoSuchElementException("Invalid Car Id");
        }
    }

    public void update(Long id, CarDto carDto) {
        validateCarDto(carDto);
        updateCarById(id, carDto);
    }

    private void validateCarDto(CarDto carDto) {
        if (carDto.getRegNum() != null && carDto.getRegNum().isBlank()) {
            throw new IllegalArgumentException("Registration No. can not be blank");
        }

        if (carDto.getBrand() != null && carDto.getBrand().isBlank()) {
            throw new IllegalArgumentException("Car Brand can not be blank");
        }
    }

    private void updateCarById(Long id, CarDto carDto) {
        Optional<Car> opCar = getCarById(id);
        if (opCar.isPresent()) {
            Car car = opCar.get();

            if (carDto.getRegNum() != null) {
                car.setRegNum(carDto.getRegNum());
            }
            if (carDto.getBrand() != null) {
                car.setBrand(carDto.getBrand());
            }

            carRepo.save(car);
        } else {
            throw new NoSuchElementException("Invalid Car Id");
        }
    }

    public void createAssociation(Long carId, Long userId) {

        Car car = carRepo.findById(carId)
                            .filter(c -> c.getStatus() == Status.AVAILABLE)
                            .orElseThrow();

        car.setOwner(String.valueOf(userId));
        car.setStatus(Status.ASSOCIATED);
        car.setAssociationDate(LocalDateTime.now());

        carRepo.save(car);
    }

    public void validateCarId(Long carId) {
        Optional<Car> opCar = getCarById(carId);
        if (opCar.isEmpty()) {
            throw new NoSuchElementException("Invalid Car Id");
        }
    }

    public void deleteAssociation(Long carId, Long userId) {
        Car car = carRepo.findById(carId)
                .filter(c -> c.getStatus() == Status.ASSOCIATED)
                .filter(c -> String.valueOf(userId).equals(c.getOwner()))
                .orElseThrow();

        car.setOwner(null);
        car.setStatus(Status.AVAILABLE);
        car.setAssociationDate(null);

        carRepo.save(car);
    }
}
