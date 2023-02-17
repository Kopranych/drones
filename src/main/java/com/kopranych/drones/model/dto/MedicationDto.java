package com.kopranych.drones.model.dto;

import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MedicationDto {

  @Pattern(regexp = "^[a-zA-Z0-9-_]*$", message = "name must match to ^[a-zA-Z0-9-_]*$")
  private String name;
  private BigDecimal weight;
  @Pattern(regexp = "^[A-Z0-9_]*$", message = "code must match to ^[A-Z0-9_]*$")
  private String code;
  private String imageUrl;
}
