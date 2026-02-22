# 🛒 E-Commerce Microservices Application

## 📌 Project Overview
This project is a backend **E-Commerce system** built using **Spring Boot Microservices architecture**.  
It handles **user authentication, product management, order processing, and payments** in a **scalable and loosely coupled way**.

Each functionality is implemented as an **independent microservice**, communicating through **REST APIs (JSON)**.

---

## 🧩 Microservices Architecture

### 1️⃣ Service Registry (Eureka Server)
**Purpose:**  
- Registers all microservices  
- Enables service discovery  

**What happens:**  
- All services register themselves with Eureka  
- Other services discover each other dynamically  

**Repository:** [eureka-server](https://github.com/divyanshupunse/ecommerce-eureka-server)

---

### 2️⃣ API Gateway
**Purpose:**  
- Single entry point for all client requests  
- Routes requests to Product, Order, Payment, and Auth services  
- Handles JWT validation and role-based access control  

**What happens:**  
- Client → API Gateway → Target Microservice  
- JWT token is validated and roles checked  
- Adds email, role, and userId as headers for downstream services  
- Blocks unauthorized access  

**Repository:** [api-gateway](https://github.com/divyanshupunse/ecommerce-api-gateway)

---

### 3️⃣ Authentication / User Service
**Purpose:**  
- Handles user registration and login  
- Generates JWT tokens for authentication  
- Applies role-based access control (CUSTOMER / ADMIN)  

**What happens:**  
- Users can signup and login  
- Passwords are securely hashed  
- JWT token is generated on login and used for API access  
- Role (CUSTOMER / ADMIN) determines permissions  

**Repository:** [auth-service](https://github.com/divyanshupunse/ecommerce-auth-service)

**Database:** `user_db`  
**User Table:** id, name, email, password, role  

---

### 4️⃣ Product Service
**Purpose:**  
- Manages the product catalog  
- Allows CRUD operations on products  
- Only ADMIN can add, update, or delete products  

**What happens:**  
- Admin adds, updates, or deletes products  
- Users (CUSTOMER / ADMIN) can view products  
- Stores product details like name, description, category, price, and stock  

**Repository:** [product-service](https://github.com/divyanshupunse/ecommerce-product-service)

**Database:** `product_db`  
**Product Table:** id, name, description, category, price, stock  

---

### 5️⃣ Order Service
**Purpose:**  
- Handles order creation, tracking, and status updates  
- Communicates with Product and Payment services via Feign clients  
- Applies role-based access control (CUSTOMER / ADMIN)  

**What happens:**  
- Users place orders; stock is checked with Product Service  
- Payment is processed via Payment Service  
- Order status updated based on payment (COMPLETED / CANCELLED)  
- Admin can view all orders and change status; users can view/cancel their own orders  

**Repository:** [order-service](https://github.com/divyanshupunse/ecommerce-order-service)

**Database:** `order_db`  
**Order Table:** id, userId, productId, quantity, totalAmount, status (PENDING, COMPLETED, CANCELLED), orderDate  

---

### 6️⃣ Payment Service
**Purpose:**  
- Handles payment processing for orders  
- Simulates payment success or failure  
- Ensures one payment per order  

**What happens:**  
- Receives payment requests from Order Service  
- Validates payment amount  
- Marks payment as SUCCESS or FAILED based on simple logic  
- Returns payment details to Order Service  

**Repository:** [payment-service](https://github.com/divyanshupunse/ecommerce-payment-service) 

**Database:** `payment_db`  
**Payment Table:** id, orderId, amount, status (SUCCESS, FAILED, PENDING), paymentDate  

---

## 🔐 JWT Security Flow
1. User logs in → Auth Service generates JWT  
2. Client sends JWT in Authorization header  
3. API Gateway validates JWT  
4. Request forwarded to microservice  
5. Microservice trusts Gateway  
6. Role-based access applied in controller  

---

## 🔄 Inter-Service Communication
- Order Service communicates with **Product Service** and **Payment Service** using **Feign Clients**  
- @GetMapping → retrieves data from other services  
- @PostMapping → sends data to other services  
- @RequestBody / @PathVariable → passes request data and parameters  

---

## 🗄️ Database Design
- **Database per service** 
- **MySQL** used  
- Each microservice has its own schema  

| Service | Database |
|---------|---------|
| User Service | user_db |
| Product Service | product_db |
| Order Service | order_db |
| Payment Service | payment_db |

---

## ⚙️ Tech Stack
- Java  
- Spring Boot  
- Spring Security + JWT  
- Spring Cloud (Eureka, API Gateway, Feign Client)  
- Spring Data JPA + Hibernate  
- MySQL  
- Git & GitHub  

---
