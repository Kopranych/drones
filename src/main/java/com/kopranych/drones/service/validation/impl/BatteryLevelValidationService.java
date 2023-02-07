package com.kopranych.drones.service.validation.impl;

import com.kopranych.drones.model.HttpException;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.service.validation.ValidationService;
import java.math.BigDecimal;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class BatteryLevelValidationService implements ValidationService<Drone> {

  private static final BigDecimal MIN_LEVEL = BigDecimal.valueOf(0.25);

  @Override
  public Drone validate(final Drone drone) {

    final var batteryCapacity = drone.getBatteryCapacity();
    if (MIN_LEVEL.compareTo(batteryCapacity) >= 0) {
      return drone;
    }
    throw new HttpException(
        HttpStatus.BAD_REQUEST,
        "Battery level %s below than min level %s for drone %s"
            .formatted(batteryCapacity, MIN_LEVEL, drone.getSerialNumber())
    );
  }
}
