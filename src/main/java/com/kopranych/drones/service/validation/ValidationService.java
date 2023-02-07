package com.kopranych.drones.service.validation;

public interface ValidationService<T> {

  T validate(T object);
}
