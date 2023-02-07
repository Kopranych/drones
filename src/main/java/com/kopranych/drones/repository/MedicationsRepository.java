package com.kopranych.drones.repository;

import com.kopranych.drones.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationsRepository extends JpaRepository<Medication, Long> {

}
