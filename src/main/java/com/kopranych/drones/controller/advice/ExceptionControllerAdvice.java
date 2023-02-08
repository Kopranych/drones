package com.kopranych.drones.controller.advice;


import com.kopranych.drones.model.HttpException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {


  @ExceptionHandler(HttpException.class)
  public ResponseEntity<ExceptionResponse> handleDecodeException(HttpException e) {
    log.error(e.getMessage(), e);
    return ResponseEntity
        .status(e.getHttpStatus())
        .body(
            new ExceptionResponse(e.getMessage())
        );
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class ExceptionResponse {

    private String message;
  }
}
