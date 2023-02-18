package com.kopranych.drones.controller;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.kopranych.drones.controller.advice.ExceptionControllerAdvice;
import com.kopranych.drones.model.DroneModel;
import com.kopranych.drones.model.DroneState;
import com.kopranych.drones.model.dto.MedicationDto;
import com.kopranych.drones.model.entity.Drone;
import com.kopranych.drones.repository.DronesRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class DispatchControllerTest {

  private static final String SERIAL_NUMBER = "123";
  @MockBean
  private DronesRepository dronesRepository;

  @Autowired
  private DispatchController dispatchController;
  @Autowired
  private ExceptionControllerAdvice exceptionControllerAdvice;

  @BeforeEach
  public void init() {
    RestAssuredMockMvc.standaloneSetup(dispatchController, exceptionControllerAdvice);
  }

  private static void putRequestProcess(
      final MedicationDto medicationDto, final String expected
  ) {
    given()
        .contentType(JSON)
        .body(medicationDto)
        .when()
        .put("/dispatch/drones/{serialNumber}/medications", SERIAL_NUMBER)
        .then()
        .statusCode(400)
        .body("message", equalTo(expected));
  }

  private static Stream<Arguments> getMedicationsAndExpectations() {

    final var expectedName = "name must match to ^[a-zA-Z0-9-_]*$";

    final var medicationName = "Medication@";
    final var medicationWrongName = new MedicationDto();
    medicationWrongName.setName(medicationName);

    final var expectedCode = "code must match to ^[A-Z0-9_]*$";

    final var code = "CODe";
    final var medicationWrongCode = new MedicationDto();
    medicationWrongCode.setCode(code);

    return Stream.of(
        Arguments.of(medicationWrongName, expectedName),
        Arguments.of(medicationWrongCode, expectedCode)
    );
  }

  @Test
  void shouldResponseWithStatusCode201OnLoadMedicationSuccessfully() {
    final var weightLimit = BigDecimal.valueOf(10);
    final var batteryLevel = BigDecimal.valueOf(0.3);
    final var drone = getDrone(DroneState.IDLE, weightLimit, batteryLevel);

    final var medicationName = "Medication";
    final var medicationWeight = BigDecimal.valueOf(9.9);
    final var medication = new MedicationDto();
    medication.setName(medicationName);
    medication.setWeight(medicationWeight);

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));
    Mockito.when(dronesRepository.save(drone)).thenReturn(drone);

    given()
        .contentType(JSON)
        .body(medication)
        .when()
        .put("/dispatch/drones/{serialNumber}/medications", SERIAL_NUMBER)
        .then()
        .statusCode(201);
  }

  @Test
  void shouldResponseWithStatusCode400OnLoadMedicationWhenMedicationWeightExceedWeightLimit() {

    final var weightLimit = BigDecimal.valueOf(10);
    final var drone = getDrone(DroneState.IDLE, weightLimit);

    final var medicationName = "Medication";
    final var medicationWeight = BigDecimal.valueOf(10.1);
    final var medication = new MedicationDto();
    medication.setName(medicationName);
    medication.setWeight(medicationWeight);

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));

    final var expected = "Medication %s weight %s exceeds weight limit %s for drone %s"
        .formatted(medicationName, medicationWeight, weightLimit, SERIAL_NUMBER);

    putRequestProcess(medication, expected);
  }

  @Test
  void shouldResponseWithStatusCode400OnLoadMedicationWhenBatteryLevelBelowThenMinLevel() {

    final var weightLimit = BigDecimal.valueOf(10);
    final var batteryLevel = BigDecimal.valueOf(0.1);
    final var drone = getDrone(DroneState.IDLE, weightLimit, batteryLevel);

    final var medicationName = "Medication";
    final var medicationWeight = BigDecimal.valueOf(9.9);
    final var medication = new MedicationDto();
    medication.setName(medicationName);
    medication.setWeight(medicationWeight);

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));

    final var expected = "Battery level %s below than min level %s for drone %s"
        .formatted(batteryLevel, BigDecimal.valueOf(0.25), SERIAL_NUMBER);

    putRequestProcess(medication, expected);
  }

  @Test
  void shouldResponseWithStatusCode400OnLoadMedicationWhenDroneStateIsNotIdle() {
    final var drone = getDrone(DroneState.LOADED, BigDecimal.valueOf(10));
    final var medicationDto = new MedicationDto();

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));

    final var expected = "Drone %s must have IDLE status for loading medication"
        .formatted(SERIAL_NUMBER);
    putRequestProcess(medicationDto, expected);
  }

  @Test
  void shouldResponseWithStatusCode400OnSaveDroneWithSerialNumberMoreThanMax() {

    final var weightLimit = BigDecimal.valueOf(10);
    final var batteryLevel = BigDecimal.valueOf(0.1);
    final var longSerialNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final var drone = getDrone(longSerialNumber, DroneState.IDLE, weightLimit, batteryLevel);

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));

    given()
        .contentType(JSON)
        .body(drone)
        .when()
        .post("/dispatch/drones")
        .then()
        .statusCode(400)
        .body("message", equalTo("serialNumber size must be between 0 and 100"));
  }

  @ParameterizedTest
  @MethodSource("getMedicationsAndExpectations")
  void shouldResponseWithStatusCode400OnLoadMedicationWithWrongFieldValues(
      final MedicationDto input, String expected
  ) {

    final var weightLimit = BigDecimal.valueOf(10);
    final var batteryLevel = BigDecimal.valueOf(0.1);
    final var drone = getDrone(DroneState.IDLE, weightLimit, batteryLevel);

    Mockito.when(dronesRepository.findById(SERIAL_NUMBER)).thenReturn(Optional.of(drone));

    putRequestProcess(input, expected);
  }

  private Drone getDrone(
      final DroneState state, final BigDecimal weightLimit, final BigDecimal batteryLevel
  ) {
    return getDrone(SERIAL_NUMBER, state, weightLimit, batteryLevel);
  }

  private Drone getDrone(final DroneState state, final BigDecimal weightLimit) {
    return getDrone(state, weightLimit, BigDecimal.valueOf(0.5));
  }

  private Drone getDrone(
      final String SerialNumber, final DroneState state,
      final BigDecimal weightLimit, final BigDecimal batteryLevel
  ) {
    final var drone = new Drone();
    drone.setSerialNumber(SerialNumber);
    drone.setState(state);
    drone.setModel(DroneModel.Cruiserweight);
    drone.setWeightLimit(weightLimit);
    drone.setBatteryCapacity(batteryLevel);
    return drone;
  }
}