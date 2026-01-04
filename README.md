# 🚗 Gallerist - Car Dealership Management System

A modern and secure **Car Dealership Sales Management System** developed with RESTful API. This project provides an end-to-end solution that enables dealerships to manage their vehicle inventory and allows customers to purchase cars.

![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-green?style=flat-square&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=flat-square&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Authentication-purple?style=flat-square)

---

## 📋 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#️-architecture)
- [Installation](#-installation)
- [Security](#-security)
- [Database Schema](#️-database-schema)
- [Usage Examples](#-usage-examples)
- [License](#-license)

---

## 🎯 About the Project

**Gallerist** is a comprehensive backend system that digitizes the operations of a car dealership. The system supports the following core functions:

1. **Dealership Management**: Registration and management of dealerships
2. **Vehicle Inventory**: Adding vehicles to the system and assigning them to dealerships
3. **Customer Management**: Customer information and account balances
4. **Car Sales**: Customers purchasing cars from dealerships
5. **Currency Rates**: Real-time exchange rates via TCMB API integration

---

## ✨ Features

### 🔐 Authentication & Authorization
- JWT (JSON Web Token) based security
- Access Token & Refresh Token mechanism
- Password hashing with BCrypt
- Stateless session management

### 💱 Currency Rate Integration
- TCMB (Central Bank of the Republic of Turkey) API integration
- Real-time USD/TRY currency conversion
- Multi-currency support for vehicle pricing (TRY, USD)

### 🚙 Car Sales Process
- Customer balance verification
- Price calculation with automatic currency conversion
- Vehicle status tracking (SALABLE, SALED)
- Automatic balance update after sale

### 📚 API Documentation
- Swagger/OpenAPI 3.0 integration
- Interactive testing with JWT Bearer Authentication support

---

## 🛠 Technology Stack

| Layer | Technology | Description |
|-------|------------|-------------|
| **Runtime** | Java 25 | Latest Java version |
| **Framework** | Spring Boot 4.0.1 | Enterprise-grade application framework |
| **Security** | Spring Security 6 | Authentication & Authorization |
| **ORM** | Spring Data JPA / Hibernate | Database operations |
| **Database** | PostgreSQL | Relational database |
| **JWT** | jjwt 0.12.6 | Token management |
| **Documentation** | SpringDoc OpenAPI 2.8.6 | API documentation |
| **Utility** | Lombok | Boilerplate code reduction |
| **Validation** | Jakarta Validation | Input validation |
| **Build** | Maven | Dependency management |

---

## 🏗️ Architecture

The project follows the **Layered Architecture** pattern:

```
┌──────────────────────────────────────────────────────────┐
│                    Controller Layer                       │
│  (REST endpoints, Request/Response handling, Validation) │
├──────────────────────────────────────────────────────────┤
│                     Service Layer                         │
│    (Business Logic, Transaction Management, DTOs)        │
├──────────────────────────────────────────────────────────┤
│                   Repository Layer                        │
│        (Data Access, JPA Repositories, Queries)          │
├──────────────────────────────────────────────────────────┤
│                     Model Layer                           │
│            (Entity classes, Enums, BaseEntity)           │
└──────────────────────────────────────────────────────────┘
```

### 🎨 Design Patterns and Best Practices

| Pattern | Usage |
|---------|-------|
| **Interface Segregation** | Interface usage in Controller and Service layers (`IRestCarController`, `ICarService`) |
| **DTO Pattern** | Data transfer with Entity-DTO separation (`DtoCarIU` - Input/Update, `DtoCar` - Output) |
| **Base Entity Pattern** | All entities extend `BaseEntity` (id, createTime) |
| **Generic Response** | Standardized API response with `RootEntity<T>` |
| **Global Exception Handling** | Centralized error management with `@ControllerAdvice` |
| **Enum-based Error Codes** | Type-safe error messages with `MessageType` enum |

---

## 🚀 Installation

### Requirements
- Java 25+
- Maven 3.8+
- PostgreSQL 15+

### Steps

1. **Clone the project**
```bash
git clone https://github.com/yourusername/gallerist.git
cd gallerist
```

2. **Create PostgreSQL database**
```sql
CREATE SCHEMA gallerist;
```

3. **Configure application properties**
```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=gallerist
spring.datasource.username=postgres
spring.datasource.password=your_password

# TCMB API (application-local.properties)
TCMB.TOKEN=your_tcmb_api_token
TCMB.URL=https://evds2.tcmb.gov.tr/service/evds/
```

4. **Start the application**
```bash
./mvnw spring-boot:run
```

5. **Access Swagger UI**
```
http://localhost:8080/swagger-ui.html
```

---

## 🔐 Security

### JWT Token Flow

```
┌─────────┐          ┌─────────┐          ┌─────────┐
│  Client │          │   API   │          │   DB    │
└────┬────┘          └────┬────┘          └────┬────┘
     │                    │                    │
     │  POST /authenticate│                    │
     │ ──────────────────>│                    │
     │                    │  Validate User     │
     │                    │ ──────────────────>│
     │                    │<───────────────────│
     │                    │                    │
     │  {accessToken,     │                    │
     │   refreshToken}    │                    │
     │ <──────────────────│                    │
     │                    │                    │
     │  GET /api/cars     │                    │
     │  Authorization:    │                    │
     │  Bearer <token>    │                    │
     │ ──────────────────>│                    │
     │                    │  JWTFilter         │
     │                    │  validates token   │
     │                    │                    │
     │     Response       │                    │
     │ <──────────────────│                    │
```

### Token Expiration Times
- **Access Token**: 2 hours
- **Refresh Token**: 4 hours

### Security Features
- ✅ CSRF protection disabled (stateless API)
- ✅ Password hashing with BCrypt
- ✅ JWT signature verification (HS256)
- ✅ Bearer auth support in Swagger UI
- ✅ Custom AuthEntryPoint for authorization errors

---

## 🗃️ Database Schema

### Entity Relationship Diagram

```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│    Address   │       │   Gallerist  │       │     Car      │
├──────────────┤       ├──────────────┤       ├──────────────┤
│ id           │◄──────│ id           │       │ id           │
│ city         │       │ firstName    │       │ plate        │
│ district     │       │ lastName     │       │ brand        │
│ neighborhood │       │ address_id   │       │ model        │
│ street       │       │ createTime   │       │ productionYear│
│ createTime   │       └──────────────┘       │ price        │
└──────────────┘              │               │ currencyType │
       ▲                      │               │ damagePrice  │
       │                      │               │ carStatusType│
       │                      ▼               │ createTime   │
       │               ┌──────────────┐       └──────────────┘
       │               │ GalleristCar │              │
       │               ├──────────────┤              │
       │               │ id           │              │
       │               │ gallerist_id │◄─────────────┤
       │               │ car_id       │──────────────┘
       │               │ createTime   │
       │               └──────────────┘
       │                      │
┌──────┴───────┐              │
│   Customer   │              ▼
├──────────────┤       ┌──────────────┐
│ id           │       │   SaledCar   │
│ firstName    │       ├──────────────┤
│ lastName     │       │ id           │
│ tckn         │◄──────│ customer_id  │
│ birthOfDate  │       │ gallerist_id │
│ address_id   │       │ car_id       │
│ account_id   │       │ createTime   │
│ createTime   │       └──────────────┘
└──────┬───────┘
       │
       ▼
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│   Account    │       │     User     │       │ RefreshToken │
├──────────────┤       ├──────────────┤       ├──────────────┤
│ id           │       │ id           │◄──────│ id           │
│ accountNo    │       │ username     │       │ refreshToken │
│ iban         │       │ password     │       │ expiredDate  │
│ amount       │       │ createTime   │       │ user_id      │
│ currencyType │       └──────────────┘       │ createTime   │
│ createTime   │                              └──────────────┘
└──────────────┘
```

---

## 📝 Usage Examples

### 1. User Registration
```json
POST /register
{
    "username": "admin",
    "password": "admin123"
}
```

### 2. Login
```json
POST /authenticate
{
    "username": "admin",
    "password": "admin123"
}

// Response
{
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR...",
    "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
}
```

### 3. Purchase a Car
```json
POST /rest/api/saled-car/buy
Authorization: Bearer <access_token>
{
    "customerId": 1,
    "galleristId": 1,
    "carId": 1
}
```

---

## 📄 License

This project is licensed under the MIT License.