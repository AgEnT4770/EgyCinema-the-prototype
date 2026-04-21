# 🎬 Cinema Service Module

This service manages the physical structure of the cinema system, including theater locations and hall configurations.

## 📌 Team Integration Info
- **Service Port:** `8080`
- **Database Name:** `cinemadb` (PostgreSQL)
- **Base API Path:** `/api/cinemas`

---

## 🛠 Features Implemented

### 1. Data Models
- **Cinema Entity:** Stores name and location.
- **Hall Entity:** Linked to a Cinema (One-to-Many). Stores hall name and total seats.

### 2. AOP Logging (Requirement Met ✅)
I have implemented **Aspect-Oriented Programming** to track all incoming requests. 
- **File:** `LoggingAspect.java`
- **Function:** Automatically logs every time a controller method is called. You will see `👉 AOP Check: Entering method...` in the IntelliJ console whenever you hit my APIs.

### 3. Dockerization (Requirement Met ✅)
- **Dockerfile:** Ready to build the production image.
- **docker-compose.yml:** Configured to spin up the PostgreSQL database container.

---

## 🚦 How to Use My API

### 1. View All Cinemas
`GET http://localhost:8080/api/cinemas`

### 2. Create a Cinema
`POST http://localhost:8080/api/cinemas`
```json
{
  "name": "Vox Cinemas",
  "location": "Mall of Egypt"
}