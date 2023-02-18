package com.kopranych.drones.service.audit.impl;

import com.kopranych.drones.model.entity.AuditBatteryLevel;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.model.event.AuditBatteryLevelEvent;
import com.kopranych.drones.repository.AuditBatteryLevelRepository;
import com.kopranych.drones.repository.DronesRepository;
import com.kopranych.drones.service.audit.AuditService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditBatteryLevelService implements AuditService<Drone, AuditBatteryLevel> {

  private final DronesRepository dronesRepository;
  private final AuditBatteryLevelRepository auditBatteryLevelRepository;

  @EventListener(AuditBatteryLevelEvent.class)
  public void auditEventListener() {
    audit();
  }

  @Override
  public void successFinishAuditLog() {
    log.info("Finish battery level audit successfully");
  }

  @Override
  public void errorFinishAuditLog(final Exception e) {
    log.error("Finish battery level audit with error {}", e.getMessage(), e);
  }

  @Override
  public void startAuditLog() {
    log.info("Start battery level audit");
  }

  @Override
  public void saveResults(final List<AuditBatteryLevel> results) {
    auditBatteryLevelRepository.saveAll(results);
  }

  @Override
  public AuditBatteryLevel auditEntity(final Drone entity) {
    return new AuditBatteryLevel(
        null, LocalDateTime.now(), entity.getSerialNumber(), entity.getBatteryCapacity()
    );
  }

  @Override
  public Stream<Drone> getEntities() {
    return dronesRepository.findAll().stream();
  }

  public Page<AuditBatteryLevel> getAll(final Pageable pageable) {
    return auditBatteryLevelRepository.findAll(pageable);
  }
}
