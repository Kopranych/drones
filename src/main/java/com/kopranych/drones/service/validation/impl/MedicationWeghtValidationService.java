package com.kopranych.drones.service.validation.impl;

import com.kopranych.drones.model.HttpException;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.service.validation.ValidationService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Order(1)
public class MedicationWeghtValidationService implements ValidationService<Drone> {

  @Override
  public Drone validate(final Drone drone) {
    final var medication = drone.getMedication();
    final var weight = medication.getWeight();
    final var weightLimit = drone.getWeightLimit();
    if (weightLimit.compareTo(weight) > 0) {
      return drone;
    }
    throw new HttpException(
        HttpStatus.BAD_REQUEST,
        "Medication %s weight %s exceeds weight limit %s for drone %s"
            .formatted(medication.getName(), weight, weightLimit, drone.getSerialNumber())
    );
  }
}
