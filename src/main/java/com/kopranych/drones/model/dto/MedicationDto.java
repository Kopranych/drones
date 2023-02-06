package com.kopranych.drones.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MedicationDto {

  private String name;
  private BigDecimal weight;
  private String code;
  private String imageUrl;
}
