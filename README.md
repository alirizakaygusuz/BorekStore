# 🥟 BorekStore - Spring Boot Backend API

**BorekStore** is a modular, layered Spring Boot application that simulates a börek (pastry) store system. It includes secure authentication, product transactions, account and store management, and real-time currency conversion support.

---

## 🚀 Features

- ✅ **JWT-based Authentication & Authorization**
- 📄 **RESTful APIs** following clean architecture (`Controller → Service → Repository`)
- 📟 **Swagger UI** with full OpenAPI documentation
- 💳 **Account Management** including balance and currency handling
- 🏪 **Store & Customer** registration and börek sale operations
- 🌍 **External API** integration for real-time exchange rates
- 🛡️ **Centralized Exception Handling** with structured error codes
- ✅ **Input Validation** using `jakarta.validation`

---

## 🧱 Tech Stack

| Layer         | Technologies                          |
|---------------|----------------------------------------|
| Backend       | Java 17, Spring Boot 3.4.4             |
| API Docs      | Springdoc OpenAPI, Swagger UI          |
| Persistence   | Spring Data JPA, Hibernate, PostgreSQL |
| Security      | Spring Security, JWT, BCrypt           |
| Dev Tools     | Lombok, MapStruct, Maven               |

---

## 📁 API Documentation

- 🔗 [Swagger UI](http://localhost:8080/swagger-ui.html)  
- 🔗 [OpenAPI Spec (JSON)](http://localhost:8080/v3/api-docs)

> 🔐 **Note:** Use the format `Bearer {your_token}` in the `Authorization` header after login.

---

## 🧪 Sample Endpoints

| Method | Endpoint                          | Description                         |
|--------|-----------------------------------|-------------------------------------|
| POST   | `/authenticate`                   | Obtain JWT token                    |
| POST   | `/register`                       | Register new user                   |
| GET    | `/rest/api/borek/list`            | List all available böreks           |
| POST   | `/rest/api/account/save`          | Create a new account                |
| DELETE | `/rest/api/address/delete/{id}`   | Delete address (if not in use)      |

---

## 🔐 Authentication Flow

1. Send `POST /authenticate` with credentials → receive `accessToken` & `refreshToken`
2. Use `Authorization: Bearer {accessToken}` in request headers
3. Refresh expired tokens via `POST /refresh-token`

---

## 🧠 Design Principles

- 🔹 **Separation of Concerns:** DTOs, entities, controllers, services are decoupled
- 🔹 **Global Error Handling:** via `BaseException` and `ErrorType` enums
- 🔹 **Robust Validation:** Request bodies validated using annotations
- 🔹 **OpenAPI Integration:** DTOs and controller methods documented via `@Schema` and `@Operation`

---

## ⚙️ Setup Instructions

```bash
# 1. Clone the project
git clone https://github.com/alirizakaygusuz/borekstore.git
cd borekstore

# 2. Update database config in src/main/resources/application.yml

# 3. Build and run
mvn clean install
mvn spring-boot:run
```
---
-✍️ Author
-Ali Rıza Kaygusuz
-👨‍💻 Backend Developer
-🌐 GitHub Profile(https://github.com/alirizakaygusuz)

📄 License
-This project is licensed under the MIT License.(https://opensource.org/licenses/MIT)

-Feel free to fork, contribute, or use it freely in your own applications.


