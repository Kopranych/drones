package com.kopranych.drones.mapper;

import com.kopranych.drones.model.dto.DroneDto;
import com.kopranych.drones.model.dto.DroneViewDto;
import com.kopranych.drones.model.dto.MedicationDto;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.model.entity.Medication;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DronesMapper {

  DronesMapper INSTANCE = Mappers.getMapper(DronesMapper.class);

  List<DroneViewDto> map(List<Drone> drone);

  DroneViewDto map(Drone drone);

  Drone map(DroneViewDto droneViewDto);

  Drone map(DroneDto droneDto);

  DroneDto mapToDto(Drone drone);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "weight", target = "weight")
  @Mapping(source = "code", target = "code")
  @Mapping(source = "imageUrl", target = "imageUrl")
  Medication map(MedicationDto medicationDto);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "weight", target = "weight")
  @Mapping(source = "code", target = "code")
  @Mapping(source = "imageUrl", target = "imageUrl")
  MedicationDto map(Medication medication);
}
