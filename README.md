# Affordmed Campus Hiring Assessment

## Components

1. Logging Middleware
2. Vehicle Maintenance Scheduler
3. Notification System Design

---

## Logging Middleware

Registration API was successfully completed and client credentials were generated.

However, the Auth API consistently returned:

```json
{
  "message": "provided fields does not match with any of our registered client"
}
```

The middleware implementation has been completed, but token generation could not be validated due to authentication issues from the assessment API.

---

## Vehicle Maintenance Scheduler

Location:

```text
vehicle_maintenance_scheduler/
```

Run:

```bash
mvn spring-boot:run
```

API:

```http
POST http://localhost:8080/scheduler
```

---

## Algorithm Used

0/1 Knapsack Dynamic Programming

Time Complexity:

O(N × Capacity)

---

## Technologies

- Java 17
- Spring Boot
- Node.js
- REST API
- Dynamic Programming