package com.kopranych.drones.model.entity;

import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Drone {

  @Id
  private String serialNumber;
  private DroneModel model;
  private BigDecimal weightLimit;
  private BigDecimal batteryCapacity;
  private DroneState state;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "medication_id")
  private Medication medication;

}
