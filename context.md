# 🧠 FitReserve - Backend Engineering Guidelines

## 📌 1. Project Context

FitReserve is a backend system designed to manage gym class reservations.

The system allows:

* Users to reserve and cancel sessions
* Administrators to manage sessions and users
* Trainers to conduct sessions

The core business logic revolves around **reservations**, including:

* Capacity validation
* Schedule conflict detection
* User state validation

The system is designed following **Domain Driven Design (DDD)** principles, ensuring alignment between business rules and technical implementation.

---

## 🏗 2. Architecture

The system must follow **Clean Architecture**, enforcing strict separation of concerns.

### Layers:

* **Domain Layer**

  * Contains entities, value objects, aggregates, and domain events
  * No external dependencies (pure business logic)

* **Application Layer**

  * Contains use cases (application services)
  * Orchestrates domain logic

* **Infrastructure Layer**

  * Database access (repositories implementation)
  * Messaging systems (Kafka optional)
  * External integrations

* **Interface Layer**

  * REST controllers (Spring Boot)
  * Handles HTTP requests/responses

### Rules:

* Domain MUST NOT depend on any other layer
* Controllers MUST NOT contain business logic
* Use cases coordinate all operations

---

## 🧩 3. Domain Driven Design (DDD)

The system is modeled using DDD concepts:

### Core Domain:

* Reservation management

### Entities:

* User
* Session
* Reservation

### Aggregates:

* Reservation (main aggregate)

### Business Rules:

* No duplicate reservations
* No schedule conflicts
* Only active users can reserve
* Capacity must not be exceeded

### Domain Events:

* ReservationCreated
* ReservationCancelled
* CapacityExceeded

---

## 🧪 4. Testing Strategy (TDD)

The system should follow **Test Driven Development (TDD)** where possible.

### Pattern:

Use **AAA (Arrange, Act, Assert)**

Example:

* Arrange → prepare data
* Act → execute use case
* Assert → verify result

### What to test:

* Business rules
* Edge cases
* Domain invariants

---

## 🧼 5. Code Quality & Clean Code

Follow Clean Code principles:

* Methods should be small (preferably < 15–20 lines)
* Use meaningful names (e.g., reserveSession, not rs)
* Avoid nested logic
* One responsibility per class

---

## 🧱 6. SOLID Principles

Apply:

* **S**: Single Responsibility → one reason to change
* **O**: Open/Closed → extend without modifying
* **L**: Liskov Substitution → interchangeable implementations
* **I**: Interface Segregation → small, focused interfaces
* **D**: Dependency Inversion → depend on abstractions

---

## ⚡ 7. Development Principles

* **DRY**: Avoid code duplication
* **KISS**: Keep solutions simple
* **YAGNI**: Do not implement unnecessary features

---

## 🔄 8. XP Practices

* Incremental development
* Continuous refactoring
* Simple design
* Fast feedback loops

---

## 🚀 9. Implementation Rules for AI

When generating code:

* Follow Clean Architecture strictly
* Do NOT mix layers
* Do NOT place business logic in controllers
* Always validate domain rules inside entities or use cases
* Use DTOs for API communication
* Use repositories only in application layer

---

## 🎯 10. Goal

Generate a scalable, maintainable backend aligned with:

* DDD
* Clean Architecture
* SOLID
* TDD
* Event Driven Design
