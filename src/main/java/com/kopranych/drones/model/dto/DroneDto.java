package com.kopranych.drones.model.dto;

import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class DroneDto {

  @Size(max = 100)
  private String serialNumber;
  private DroneModel model;
  private BigDecimal weightLimit;
  private BigDecimal batteryCapacity;
  private DroneState state;

}
