package com.kopranych.drones.model.dto;

import lombok.Data;

@Data
public class DroneViewDto extends DroneDto {

  private MedicationDto medication;
}
