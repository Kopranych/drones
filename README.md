### Build project

``
./gradlew build --no-daemon --stacktrace
``

### Run project

``
java -jar drones.jar
``

``
After start project will create drone and medication tables also it will insert example drone rows
``

### API

``
GET
http://localhost:8080/dispatch/drones - get all drones or select by query params
``

``
example:
http://localhost:8080/dispatch/drones?model=Heavyweight&state=IDLE&minBatteryLevel=0.1
``

``
POST
http://localhost:8080/dispatch/drones - save new drone
``

``
example:
``

```json
{
    "serialNumber": 140,
    "model": "Middleweight",
    "weightLimit": 100,
    "batteryCapacity": 0.5,
    "state": "IDLE"
}
```

``
PUT
http://localhost:8080/dispatch/drones/{serialNumber}/medications - load medication to drone
``

``
example:
``

```json
 {
    "name": "First",
    "weight": 5,
    "code": "11232",
    "imageUrl": "http://cdn-host/medications/First"
}

```

``
DELETE
http://localhost:8080/dispatch/drones/{serialNumber} - delete drone by serialNumber
``
