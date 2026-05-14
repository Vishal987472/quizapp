# Online Quiz Application Backend

An enterprise-level backend system for an Online Quiz Application built using Spring Boot and PostgreSQL.  
The project provides secure authentication, quiz management, question management, quiz attempts, leaderboards, analytics, and Dockerized deployment.

---

# 🚀 Features

## 🔐 Authentication & Authorization
- JWT-based authentication
- Spring Security integration
- Role-Based Access Control (RBAC)
- BCrypt password encryption
- Admin/User authorization

---

## 📝 Quiz Management
- Create quizzes
- Update quizzes
- Delete quizzes
- Get quizzes with pagination
- Sorting and filtering support
- Difficulty levels

---

## ❓ Question Management
- Add questions to quizzes
- Update questions
- Delete questions
- Fetch questions by quiz

---

## 🧠 Quiz Attempt Engine
- Submit quiz answers
- Automatic answer evaluation
- Score calculation
- Percentage calculation
- Quiz attempt tracking

---

## 🏆 Leaderboard & Analytics
- Global leaderboard
- Quiz-wise leaderboard
- Average score analytics
- Highest and lowest score tracking

---

## 📄 API Documentation
- Swagger/OpenAPI integration
- JWT authorization inside Swagger UI

---

## 🐳 Docker Support
- Dockerized Spring Boot application
- Dockerized PostgreSQL database
- Docker Compose support

---

# 🛠️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend | Spring Boot |
| Database | PostgreSQL |
| ORM | Spring Data JPA |
| Security | Spring Security + JWT |
| Validation | Hibernate Validator |
| Documentation | Swagger/OpenAPI |
| Build Tool | Maven |
| Containerization | Docker & Docker Compose |

---

# 📂 Project Structure

```text
src/main/java/com/quizapp
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── security
├── service
│   └── impl