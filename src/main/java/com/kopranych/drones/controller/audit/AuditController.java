package com.kopranych.drones.controller.audit;

import com.kopranych.drones.model.entity.AuditBatteryLevel;
import com.kopranych.drones.model.event.AuditBatteryLevelEvent;
import com.kopranych.drones.service.audit.impl.AuditBatteryLevelService;
import java.time.Clock;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        new AuditBatteryLevelEvent(this, Clock.systemDefaultZone())
    );
  }

  @GetMapping
  public Page<AuditBatteryLevel> get(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "100") Integer size,
      @RequestParam(defaultValue = "DESC") Direction direction
  ) {
    return auditBatteryLevelService.getAll(
        PageRequest.of(page, size, Sort.by(direction, "dateTime"))
    );
  }
}
