# 🛒 E-Commerce Microservices Application

## 📌 Project Overview
This project is a backend **E-Commerce system** built using **Spring Boot Microservices architecture**.  
It is designed to handle user authentication, product management, order processing, and payments in a **scalable and loosely coupled** way.

Each functionality is developed as an **independent microservice**, communicating through REST APIs.

---

## 🧩 Microservices Architecture

### 1️⃣ Service Registry (Eureka Server)
**Purpose:**
- Registers all microservices
- Enables service discovery
- Removes hard-coded service URLs

**What happens:**
- All services register themselves with Eureka
- Other services discover each other dynamically

---

### 2️⃣ API Gateway
**Purpose:**
- Single entry point for all client requests
- Routes requests to appropriate microservices
- Central place for security & logging

**What happens:**
- JWT validation happens here
- Blocks unauthorized access

---

### 3️⃣ Authentication / User Service
**Purpose:**
- User registration and login
- JWT token generation
- Role-based access control

**Features:**
- User signup (CUSTOMER / ADMIN)
- Login with JWT
- Secure password storage

**Flow:**

---

### 4️⃣ Product Service
**Purpose:**
- Manage product catalog

**Features:**
- Add / update / delete products (ADMIN only)
- View products (PUBLIC)
- Product category & price management

**Flow:**
- Admin adds products
- Users fetch product list
- Data stored in Product DB

---

### 5️⃣ Order Service
**Purpose:**
- Handle order creation and tracking

**Features:**
- Place order
- View order history
- Order status management

**Flow:**
- User places order
- Order service checks product availability
- Order details saved

---

### 6️⃣ Payment Service (Mock)
**Purpose:**
- Simulate payment processing

**Features:**
- Process payment
- Update payment status
- Link payment with order

**Flow:**
- Order Service calls Payment Service
- Payment success/failure response returned

---

## 🔐 Security Flow (JWT Based)
1. User logs in → receives JWT token
2. Token sent in Authorization header
3. API Gateway validates JWT
4. Request forwarded to microservice
5. Role-based access applied

---

## 🔄 Inter-Service Communication
- **Feign Client** used for service-to-service calls
- Services communicate using **service names**, not URLs

**Example:**

---

## 🗄️ Database Design
- Database per service (Loose coupling)
- MySQL used
- Each microservice has its own schema

| Service           | Database      |
|------------------|---------------|
| User Service      | user_db       |
| Product Service   | product_db    |
| Order Service     | order_db      |
| Payment Service   | payment_db    |

---

## ⚙️ Tech Stack
- **Java**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Cloud** (Eureka, Gateway, Feign)
- **Spring Data JPA + Hibernate**
- **MySQL**
- **Git & GitHub**

---

