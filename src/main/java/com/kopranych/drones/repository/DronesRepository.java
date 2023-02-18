package com.kopranych.drones.repository;

import com.kopranych.drones.model.entity.Drone;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DronesRepository extends JpaRepository<Drone, String> {

  Stream<Drone> streamAll();
}
