package com.kopranych.drones.model;

import lombok.Data;

@Data
public class DroneDto {

  private String serialNumber;
  private DroneModel model;
  private Integer weightLimit;
  private Double batteryCapacity;
  private DroneState state;


}
