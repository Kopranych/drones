package com.kopranych.drones.controller;

import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.dto.DroneDto;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DronesController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save(DroneDto droneDto) {

  }

  @GetMapping("/{serialNumber}")
  public List<DroneDto> get(
      @PathVariable(required = false) final String serialNumber,
      @RequestParam(required = false) final DroneModel model,
      @RequestParam(required = false) final DroneState state
  ) {
    return Collections.emptyList();
  }

  @PutMapping
  public void update(DroneDto droneDto) {

  }

  @DeleteMapping("/{serialNumber}")
  public void delete(@PathVariable String serialNumber) {

  }
}
