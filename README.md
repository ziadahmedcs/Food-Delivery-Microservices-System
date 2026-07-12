# 🍔 Food Delivery Microservices System

A scalable **Food Delivery Backend** built using **Java Spring Boot Microservices Architecture**.

The project follows a distributed architecture where each service is responsible for a specific business domain and communicates with other services using **OpenFeign**. Service discovery is handled by **Eureka Server**, while all client requests pass through an **API Gateway**.

---

# 🚀 Features

- JWT Authentication & Authorization
- Role-Based Access Control (RBAC)
- Microservices Architecture
- API Gateway
- Eureka Service Discovery
- OpenFeign Inter-Service Communication
- RESTful APIs
- Request Validation
- Global Exception Handling
- Layered Architecture
- MySQL Database
- Spring Data JPA & Hibernate
- Secure Endpoints with Spring Security

---

# 🏗️ Architecture

```
                     Client
                        │
                 API Gateway
                        │
        ┌───────────────┼────────────────┐
        │               │                │
        │               │                │
   Auth Service   Restaurant Service   Menu Service
        │               │                │
        └───────────────┼────────────────┘
                        │
                 Eureka Server
```

---

# 📦 Microservices

## 🔐 Auth Service

Responsible for:

- User Registration
- User Login
- JWT Generation
- JWT Validation
- Role Management
- Current User Information

Roles:

- ADMIN
- RESTAURANT_OWNER
- CUSTOMER

---

## 🍽 Restaurant Service

Responsible for:

- Create Restaurant
- Update Restaurant
- Delete Restaurant
- Get Restaurant
- Get All Restaurants
- Restaurant Ownership Validation

---

## 📋 Menu Service

Responsible for:

### Menu Categories

- Create Category
- Update Category
- Delete Category
- Get Category
- Get Categories By Restaurant

### Menu Items

- Create Item
- Update Item
- Delete Item
- Get Item By Id
- Get Items By Category
- Get Restaurant Menu

---

# 🛠 Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud
- Spring Data JPA
- Hibernate
- OpenFeign
- Eureka Server
- API Gateway
- JWT
- Maven
- MySQL
- Lombok

---

# 📁 Project Structure

```
Food-Delivery-Microservices-System
│
├── api-gateway
├── auth-service
├── discovery-server
├── restaurant-service
└── menu-service
```

---

# 🔐 Authentication

Authentication is implemented using **JWT**.

After a successful login, the client receives a JWT token that must be included in every secured request.

Example:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 🔄 Inter-Service Communication

The project uses **OpenFeign** for communication between services.

Example:

- Menu Service → Restaurant Service
- Restaurant Service → Auth Service

---

# 🌐 Service Discovery

All services register automatically with **Eureka Server**.

Services communicate using their service names instead of fixed URLs.

---

# 🗄 Database

Each microservice has its own database following the **Database per Service** pattern.

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/ziadahmedcs/Food-Delivery-Microservices-System.git
```

## Start Services

Run the services in the following order:

1. Eureka Server
2. API Gateway
3. Auth Service
4. Restaurant Service
5. Menu Service

---

# 🔮 Future Improvements

- Order Service
- Payment Service
- Notification Service
- Docker
- Docker Compose
- Kafka Integration
- Redis Caching
- Swagger API Documentation
- Unit Testing
- Integration Testing

---

# 👨‍💻 Author

**Ziad Ahmed**

- Java Backend Developer
- Spring Boot Developer
- Computer Science Graduate

GitHub:
https://github.com/ziadahmedcs

---

## ⭐ If you like this project, don't forget to give it a Star!
