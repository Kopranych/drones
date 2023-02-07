package com.kopranych.drones.service.validation.impl;

import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.HttpException;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.service.validation.ValidationService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Order(0)
public class IdleStateValidationService implements ValidationService<Drone> {

  @Override
  public Drone validate(final Drone drone) {
    if (drone.getState() == DroneState.IDLE) {
      return drone;
    }
    throw new HttpException(
        HttpStatus.BAD_REQUEST,
        "Drone %s must have IDLE status for loading medication".formatted(drone.getSerialNumber())
    );
  }
}
