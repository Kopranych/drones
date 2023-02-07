package com.kopranych.drones.service;

import java.util.Optional;

public interface CrudService<T, ID> {

  T save(T object);

  Optional<T> get(ID id);

  void delete(ID id);
}
