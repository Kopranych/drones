package com.kopranych.drones.service.impl;

import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.HttpException;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.model.entity.Medication;
import com.kopranych.drones.repository.DronesRepository;
import com.kopranych.drones.service.CrudService;
import com.kopranych.drones.service.validation.ValidationService;
import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService implements CrudService<Drone, String> {

  private final List<ValidationService<Drone>> validationServices;

  private final DronesRepository dronesRepository;

  @Override
  public Drone save(final Drone object) {
    return dronesRepository.save(object);
  }

  @Override
  public Optional<Drone> get(final String id) {
    return dronesRepository.findById(id);
  }

  public List<Drone> get(
      @Nullable final String serialNumber,
      @Nullable final DroneModel model,
      @Nullable final DroneState state,
      @Nullable final BigDecimal minBatteryLeve
  ) {
    return Collections.emptyList();
  }

  @Override
  public void delete(final String id) {
    dronesRepository.deleteById(id);
  }

  public Drone loadMedication(final String serialNumber, final Medication medication) {
    return get(serialNumber)
        .map(drone -> {
          drone.setMedication(medication);
          return drone;
        })
        .map(drone -> {
          for (ValidationService<Drone> validationService : validationServices) {
            drone = validationService.validate(drone);
          }
          return save(drone);
        })
        .orElseThrow(() -> new HttpException(
                HttpStatus.NOT_FOUND,
                "Not found drone with serialNumber %s".formatted(serialNumber)
            )
        );
  }
}
