# 🍭 Sweet Shop Management System

A full-stack web application built using **Spring Boot (Backend)** and **React (Frontend)** for managing sweets, inventory, users, and purchases.

---

## 🚀 Features

### 🔐 Backend
- **JWT Authentication** for secure login/registration  
- **Role-Based Access Control** (Admin & Customer)  
- **RESTful API** with full CRUD operations  
- **Spring Data JPA** with MySQL/H2 integration  
- **Search & Filter** sweets by category, name, price  
- **Inventory Management** (Purchase & Restock)  

### 💻 Frontend
- **React 18 Single Page Application**  
- **JWT-based Authentication**  
- **Responsive UI**  
- **Sweet Listing & Purchase Flow**  
- **Real-Time Stock Updates**  

---

## 🛠 Tech Stack

### Backend
- Java 11+
- Spring Boot 2.7
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- H2 / MySQL
- Maven

### Frontend
- React 18
- JavaScript ES6+
- CSS3



---

## 📌 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

---

### 🍬 Sweets Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/sweets` | Fetch all sweets |
| POST | `/api/sweets` | Add sweet *(Admin only)* |
| GET | `/api/sweets/{id}` | Get sweet by ID |
| PUT | `/api/sweets/{id}` | Update sweet *(Admin only)* |
| DELETE | `/api/sweets/{id}` | Delete sweet *(Admin only)* |
| GET | `/api/sweets/search` | Search sweets by name/category/price |

---

### 📦 Inventory Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/sweets/{id}/purchase` | Purchase sweet (decrease stock) |
| POST | `/api/sweets/{id}/restock` | Restock sweet *(Admin only)* |

---

## 🏃‍♂️ Quick Start

### ✔️ Prerequisites
- Java 17+
- Node.js 14+
- MySQL installed locally

---

<img width="1528" height="753" alt="Screenshot 2025-11-15 050515" src="https://github.com/user-attachments/assets/3a815e23-9bd5-452e-b7ab-54e3c4b331c0" />

<img width="1803" height="819" alt="Screenshot 2025-11-15 050542" src="https://github.com/user-attachments/assets/d9bd5a59-1614-498f-99ed-e1668249e486" />
<img width="1351" height="815" alt="Screenshot 2025-11-15 042504" src="https://github.com/user-attachments/assets/9a284794-c4bb-49b2-bc2f-3a8b517ce36f" />


## 🤖 My AI Usage

### **AI Tools Used**
- **ChatGPT/OpenAI Assistant** - Primary AI development partner
- **GitHub Copilot** - Code completion and suggestions

### **How I Used AI**

#### 🏗️ Spring Boot Structure
- Generated initial project setup and configuration
- Created entity classes, repositories, and service layers
- Set up Spring Security and dependency configurations

#### 🔐 JWT Implementation  
- Created complete authentication system with security configuration
- Implemented JWT token generation and validation
- Built authentication filters and security chains

#### 🌐 API Design
- Assisted with REST endpoint structure and DTO patterns
- Designed request/response models for all API endpoints
- Implemented proper HTTP status codes and error handling

#### ⚛️ React Development
- Built frontend components with API integration
- Created authentication flows and protected routes
- Implemented state management and API service layers

#### 🐛 Problem Solving
- Debugged technical issues and dependency conflicts
- Resolved port conflicts and Spring Boot configuration issues
- Fixed CORS issues and API integration problems

#### 🗄️ Database Integration
- Set up entity relationships and repository patterns
- Implemented JPA configurations and query methods
- Created database migrations and seed data

### **Reflection**
AI tools significantly accelerated the development process, particularly for complex features like JWT authentication and Spring Security configuration. The AI served as a collaborative partner during technical challenges, providing multiple solutions and helping overcome steep learning curves. 

This experience demonstrated the importance of balancing AI assistance with personal understanding - while AI can generate code quickly, critical thinking and problem-solving skills remain essential for creating robust, maintainable applications. The partnership allowed for rapid prototyping while ensuring code quality through careful review and customization of AI-generated solutions.

The most valuable insight was learning to leverage AI as a force multiplier rather than a replacement for developer expertise, creating a synergy that delivered a production-ready application in record time.


## ▶️ Backend Setup (Spring Boot)

```bash
cd backend
mvn spring-boot:run

Server starts on http://localhost:8080




Built with Spring Boot & React




