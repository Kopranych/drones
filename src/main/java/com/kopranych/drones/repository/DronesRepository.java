package com.kopranych.drones.repository;

import com.kopranych.drones.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DronesRepository extends JpaRepository<Drone, String> {

}
