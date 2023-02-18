package com.kopranych.drones.service;

import java.util.Optional;

public interface CrudService<T, I> {

  T save(T object);

  Optional<T> get(I id);

  void delete(I id);
}
