package com.hobbyproject.carrentalservice.api.mapper;

import com.hobbyproject.carrentalservice.api.dto.CarDto;
import com.hobbyproject.carrentalservice.entity.Car;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>EntityToDtoMapper</h2>
 * @date 2024-04-02
 */

public class EntityToDtoMapper {

    public static CarDto mapToCarDto(Car car) {

        return CarDto.builder()
                .regNum(car.getRegNum())
                .brand(car.getBrand())
                .status(String.valueOf(car.getStatus()))
                .owner(car.getOwner())
                .associationDate(car.getAssociationDate())
                .build();
    }
}
