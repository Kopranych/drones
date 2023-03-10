package com.kopranych.drones.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

  private HttpStatus httpStatus;

  private String message;

  public HttpException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.message = message;
  }

}
