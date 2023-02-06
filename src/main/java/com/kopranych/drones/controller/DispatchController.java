package com.kopranych.drones.controller;

import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.dto.DroneDto;
import com.kopranych.drones.model.dto.DroneViewDto;
import com.kopranych.drones.model.dto.MedicationDto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

  @PostMapping("/drones")
  @ResponseStatus(HttpStatus.CREATED)
  public void saveDrone(@RequestBody DroneDto droneDto) {

  }

  @PutMapping("/drones/{id}/medications")
  @ResponseStatus(HttpStatus.CREATED)
  public void loadMedication(
      @PathVariable(name = "id") String serialNumber,
      @RequestBody MedicationDto medicationDto
  ) {

  }

  @GetMapping("/drones/{id}")
  public List<DroneViewDto> get(
      @PathVariable(name = "id", required = false) final String serialNumber,
      @RequestParam(required = false) final DroneModel model,
      @RequestParam(required = false) final DroneState state,
      @RequestParam(required = false) final BigDecimal minBatteryLevel
  ) {
    return Collections.emptyList();
  }

}
