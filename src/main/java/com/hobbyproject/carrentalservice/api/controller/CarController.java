package com.hobbyproject.carrentalservice.api.controller;

import com.hobbyproject.carrentalservice.api.dto.CarDto;
import com.hobbyproject.carrentalservice.api.mapper.EntityToDtoMapper;
import com.hobbyproject.carrentalservice.entity.Car;
import com.hobbyproject.carrentalservice.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CarController</h2>
 * @date 2024-04-02
 */

@RestController
@RequestMapping("/api/v1")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDto> getAllCars() {
        List<Car> allCars = carService.getAllCars();
        return allCars.stream()
                .map(car -> EntityToDtoMapper.mapToCarDto(car))
                .toList();
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@Valid @RequestBody CarDto carDto) {
        Car savedCar = carService.createCar(carDto);

        return EntityToDtoMapper.mapToCarDto(savedCar);
    }

    @GetMapping("/cars/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        Optional<Car> opCar = carService.getCarById(id);
        if (opCar.isPresent()) {
            Car car = opCar.get();
            return EntityToDtoMapper.mapToCarDto(car);
        } else {
            return null;
        }
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }

    @PatchMapping("/cars/{id}")
    public void updateCarById(@PathVariable("id") Long id,
                              @Valid @RequestBody CarDto carDto) {
        carService.update(id,carDto);
    }

    @PostMapping("/cars/{carId}/users/{userId}")
    public void createAssociation(@PathVariable("carId") Long carId,
                                    @PathVariable("userId") Long userId) {
        carService.validateCarId(carId);
        carService.createAssociation(carId, userId);
    }

    @DeleteMapping("/cars/{carId}/users/{userId}")
    public void deleteAssociation(@PathVariable("carId") Long carId,
                                  @PathVariable("userId") Long userId) {
        carService.validateCarId(carId);
        carService.deleteAssociation(carId, userId);
    }
}
