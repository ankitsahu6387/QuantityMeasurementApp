# Quantity Measurement Application

A Java application built progressively through use cases, starting from basic unit equality checks and evolving into a full Spring Boot REST API with JWT authentication.

---

## Branches

### UC1 — `feature/UC1-FeetEquality`
First use case. Checks if two quantities in feet are equal. Introduces the basic `Length` class and equality logic.

### UC2 — `feature/UC2-InchEquality`
Extends UC1 to support inches. Adds conversion between feet and inches for equality comparison.

### UC3 — `feature/UC3-GenericLength`
Refactors length handling to support multiple units generically. Introduces the `LengthUnit` enum with conversion factors.

### UC4 — `feature/UC4-YardEquality`
Adds yard support to the length measurement system. Extends the unit enum with yard conversion.

### UC5 — `feature/UC5-UnitConversion`
Introduces unit conversion — converting a quantity from one unit to another (e.g. feet to inches). Adds `convertTo()` method.

### UC6 — `feature/UC6-UnitAddition`
Adds support for adding two length quantities. Result is returned in the first operand's unit.

### UC7 — `feature/UC7-TargetUnitAddition`
Extends addition to allow specifying a target unit for the result (e.g. add 1 FOOT + 12 INCHES, result in YARDS).

### UC8 — `feature/UC8-StandaloneUnit`
Refactors `LengthUnit` from an inner class to a standalone top-level enum. Improves code organization and reusability.

### UC9 — `feature/UC9-Weight-Measurement`
Introduces weight measurement support. Adds `WeightUnit` enum (KILOGRAM, GRAM, POUND) with conversion logic.

### UC10 — `feature/UC10-GenericQuantity`
Major refactor. Introduces the generic `Quantity<U extends IMeasurable>` class and `IMeasurable` interface, replacing separate `Length` and `Weight` classes with a single reusable generic class.

### UC11 — `feature/UC11-VolumeMeasurement`
Adds volume measurement support. Introduces `VolumeUnit` enum (LITRE, MILLILITRE, GALLON) using the existing generic `Quantity<U>` class without any changes to core logic.

### UC12 — `feature/UC12-SubtractionAndDivision`
Adds subtraction and division operations to `Quantity<U>`. Division returns a dimensionless scalar. Includes divide-by-zero handling.

### UC13 — `feature/UC13-CentralizedArithmeticLogic`
Internal refactor of arithmetic operations. Introduces a private `ArithmeticOperation` enum with lambdas and centralizes validation into shared helper methods. Public API unchanged.

### UC14 — `feature/UC14-TemperatureMeasurement`
Adds temperature support with `TemperatureUnit` enum (CELSIUS, FAHRENHEIT). Temperatures use formula-based conversion instead of simple multiplication. Arithmetic operations on temperature are intentionally unsupported and throw `UnsupportedOperationException`.

### UC15 — `feature/UC15-NTier`
Refactors the monolithic application into N-Tier architecture with distinct layers — Application, Controller, Service, Repository, and Entity. Introduces DTOs (`QuantityDTO`, `QuantityModel`, `QuantityMeasurementEntity`), dependency injection, and the repository pattern with an in-memory cache.

### UC16 — `feature/UC16-DatabaseIntegration`
Replaces the in-memory cache with a JDBC-backed H2 database repository. Introduces connection pooling (`ConnectionPool`), `ApplicationConfig` for property loading, parameterized SQL, transaction management, and `DatabaseException`. Project migrated to Maven standard layout.

### UC17 — `feature/UC17-SpringBackend`
Transforms the application into a Spring Boot REST API. Introduces `@RestController`, `@Service`, Spring Data JPA replacing manual JDBC, H2 auto-configured via JPA, Swagger UI for API documentation, and `@ControllerAdvice` for global exception handling. REST endpoints exposed for all quantity operations.

### UC18 — `feature/UC18-Google-Authnetication`
Adds JWT-based authentication to the Spring Boot application. Introduces user registration and login endpoints, BCrypt password hashing, JWT token generation and validation, and a `JwtAuthFilter` that protects all API endpoints. All quantity endpoints require a valid Bearer token.

---

```
POST /auth/register       - Register a new user
POST /auth/login          - Login and get JWT token

POST /api/v1/quantities/compare    - Compare two quantities
POST /api/v1/quantities/convert    - Convert a quantity to another unit
POST /api/v1/quantities/add        - Add two quantities
POST /api/v1/quantities/subtract   - Subtract two quantities
POST /api/v1/quantities/divide     - Divide two quantities
GET  /api/v1/quantities/history    - Get all operation history
GET  /api/v1/quantities/history/{operation} - Filter history by operation
GET  /api/v1/quantities/count/{operation}   - Count operations by type
```