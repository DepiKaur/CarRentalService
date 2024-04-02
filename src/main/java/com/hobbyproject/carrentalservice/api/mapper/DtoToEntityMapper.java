package com.hobbyproject.carrentalservice.api.mapper;

import com.hobbyproject.carrentalservice.api.dto.CarDto;
import com.hobbyproject.carrentalservice.entity.Car;
import com.hobbyproject.carrentalservice.entity.Status;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>DtoToEntityMapper</h2>
 * @date 2024-04-02
 */
public class DtoToEntityMapper {

    public static Car mapToCar(CarDto carDto) {

        return Car.builder()
                .regNum(carDto.getRegNum())
                .brand(carDto.getBrand())
                .status(Status.valueOf(carDto.getStatus()))
                .owner(carDto.getOwner())
                .associationDate(carDto.getAssociationDate())
                .build();
    }
}
