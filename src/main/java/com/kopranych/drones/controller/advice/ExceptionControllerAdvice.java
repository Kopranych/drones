package com.kopranych.drones.controller.advice;


import com.kopranych.drones.model.HttpException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        .body(new ExceptionResponse(e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidationException(
      MethodArgumentNotValidException e) {
    log.error(e.getMessage(), e);

    var bindingResult = e.getBindingResult();
    var messages = bindingResult.getFieldErrors()
        .stream()
        .map(fieldError -> {
          var field = fieldError.getField();
          var defaultMessage = fieldError.getDefaultMessage();
          if (defaultMessage != null && defaultMessage.contains(field)) {
            return defaultMessage;
          }
          return field + " " + defaultMessage;
        })
        .toList();
    var message = String.join(", ", messages);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionResponse(message));
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class ExceptionResponse {

    private String message;
  }
}
