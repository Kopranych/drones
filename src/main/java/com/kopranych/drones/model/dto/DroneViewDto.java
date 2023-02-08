package com.kopranych.drones.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DroneViewDto extends DroneDto {

  private MedicationDto medication;
}
