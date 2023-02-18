package com.kopranych.drones.service.audit;

import java.util.List;
import java.util.stream.Stream;

public interface AuditService<T, R> {

  default void audit() {
    try {
      startAuditLog();
      List<R> results = getEntities()
          .map(this::auditEntity)
          .toList();
      saveResults(results);
      successFinishAuditLog();
    } catch (Exception e) {
      errorFinishAuditLog(e);
    }
  }

  void successFinishAuditLog();

  void errorFinishAuditLog(Exception e);

  void startAuditLog();

  void saveResults(List<R> results);

  R auditEntity(T entity);

  Stream<T> getEntities();

}
