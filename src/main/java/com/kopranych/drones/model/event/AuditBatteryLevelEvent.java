package com.kopranych.drones.model.event;

import java.time.Clock;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AuditBatteryLevelEvent extends ApplicationEvent {

  public AuditBatteryLevelEvent(Object source, Clock clock) {
    super(source, clock);
  }
}
