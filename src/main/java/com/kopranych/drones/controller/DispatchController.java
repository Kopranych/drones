package com.kopranych.drones.controller;

import com.kopranych.drones.mapper.DronesMapper;
import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.dto.DroneDto;
import com.kopranych.drones.model.dto.DroneViewDto;
import com.kopranych.drones.model.dto.MedicationDto;
import com.kopranych.drones.service.impl.DispatchService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequiredArgsConstructor
public class DispatchController {

  private final DispatchService dispatchService;

  @PostMapping("/drones")
  @ResponseStatus(HttpStatus.CREATED)
  public void saveDrone(@RequestBody @Valid DroneDto droneDto) {
    dispatchService.save(DronesMapper.INSTANCE.map(droneDto));
  }

  @PutMapping("/drones/{serialNumber}/medications")
  @ResponseStatus(HttpStatus.CREATED)
  public DroneViewDto loadMedication(
      @PathVariable String serialNumber,
      @RequestBody @Valid MedicationDto medicationDto
  ) {
    final var drone = dispatchService.loadMedication(
        serialNumber, DronesMapper.INSTANCE.map(medicationDto)
    );
    return DronesMapper.INSTANCE.map(drone);
  }

  @GetMapping("/drones")
  public List<DroneViewDto> get(
      @RequestParam(name = "id", required = false) final String serialNumber,
      @RequestParam(required = false) final DroneModel model,
      @RequestParam(required = false) final DroneState state,
      @RequestParam(required = false) final BigDecimal minBatteryLevel
  ) {
    return DronesMapper.INSTANCE.map(
        dispatchService.get(serialNumber, model, state, minBatteryLevel));
  }

  @DeleteMapping("/drones/{serialNumber}")
  public void delete(@PathVariable String serialNumber) {
    dispatchService.delete(serialNumber);
  }

}
