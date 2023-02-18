package com.kopranych.drones.repository;

import com.kopranych.drones.model.entity.AuditBatteryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditBatteryLevelRepository extends JpaRepository<AuditBatteryLevel, Long> {

}
