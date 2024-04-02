package com.hobbyproject.carrentalservice.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CarDto</h2>
 * @date 2024-04-02
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

    @JsonProperty(value = "reg_num")
    private String regNum;

    @JsonProperty(value = "brand")
    private String brand;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "owner")
    private String owner;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(value = "association_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime associationDate;
}
