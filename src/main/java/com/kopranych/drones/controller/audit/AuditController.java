package com.kopranych.drones.controller.audit;

import com.kopranych.drones.model.entity.AuditBatteryLevel;
import com.kopranych.drones.model.event.AuditBatteryLevelEvent;
import com.kopranych.drones.service.audit.impl.AuditBatteryLevelService;
import java.time.Clock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones/audit")
@RequiredArgsConstructor
public class AuditController {

  private final ApplicationEventPublisher applicationEventPublisher;
  private final AuditBatteryLevelService auditBatteryLevelService;

  @PostMapping
  public void startAudit() {
    applicationEventPublisher.publishEvent(
        new AuditBatteryLevelEvent(this, Clock.systemDefaultZone()));
  }

  @GetMapping
  public List<AuditBatteryLevel> get() {
    return auditBatteryLevelService.getAll();
  }
}
