# Notification System Design

## Stage 1 - API Design

### Notification Object

```json
{
  "id": "uuid",
  "studentId": 1042,
  "type": "Placement",
  "message": "Amazon Hiring",
  "isRead": false,
  "createdAt": "2026-04-22T17:51:30Z"
}
```

### APIs

#### Get Notifications

```http
GET /api/v1/notifications
```

#### Get Notification By Id

```http
GET /api/v1/notifications/{id}
```

#### Create Notification

```http
POST /api/v1/notifications
```

#### Mark Notification As Read

```http
PATCH /api/v1/notifications/{id}/read
```

#### Delete Notification

```http
DELETE /api/v1/notifications/{id}
```

### Real-Time Notifications

Use WebSocket for instant notification delivery.

Architecture:

Client → WebSocket Gateway → Notification Service

Benefits:

* Real-time updates
* Reduced polling
* Better user experience

---

## Stage 2 - Database Design

### Database Choice

PostgreSQL

Reasons:

* ACID transactions
* Strong indexing
* Reliable and scalable

### Students Table

```sql
CREATE TABLE students(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(255) UNIQUE
);
```

### Notifications Table

```sql
CREATE TABLE notifications(
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT,
    notification_type VARCHAR(20),
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP
);
```

### Index

```sql
CREATE INDEX idx_student_read_created
ON notifications(
student_id,
is_read,
created_at DESC
);
```

---

## Stage 3 - Query Optimization

Original Query

```sql
SELECT *
FROM notifications
WHERE studentID = 1042
AND isRead = false
ORDER BY createdAt DESC;
```

Problems:

* Full table scan
* Sorting overhead
* Returns unnecessary columns

Optimized Query

```sql
SELECT id,
notification_type,
message,
created_at
FROM notifications
WHERE student_id = 1042
AND is_read = false
ORDER BY created_at DESC;
```

Composite Index

```sql
CREATE INDEX idx_student_read_created
ON notifications(
student_id,
is_read,
created_at DESC
);
```

Placement Notifications in Last 7 Days

```sql
SELECT *
FROM notifications
WHERE notification_type='Placement'
AND created_at >= NOW() - INTERVAL '7 days';
```

---

## Stage 4 - Performance Improvement

Problem:

Notifications are fetched from database on every page refresh.

Solution:

### Redis Cache

Client
↓
Redis
↓ Cache Miss
Database

Benefits:

* Faster reads
* Reduced database load

### Pagination

```http
GET /notifications?page=1&size=20
```

### Infinite Scrolling

Load data only when needed.

---

## Stage 5 - Notify All Optimization

Current Problem:

```python
for student in students:
    send_email()
    save_to_db()
    push_notification()
```

Issues:

* Sequential processing
* Slow
* Not fault tolerant

### Improved Architecture

Producer
↓
Kafka
↓
-

↓         ↓          ↓
Email    DB       Push

### Improved Pseudocode

```python
publish(notification)
```

Workers consume independently.

Benefits:

* Faster execution
* Retry support
* Better scalability

---

## Stage 6 - Priority Inbox

Priority Weight

Placement = 3

Result = 2

Event = 1

Priority Score

```
(weight * 1000) + timestamp
```

Sort notifications by score in descending order.

Return Top 10 highest priority notifications.

Benefits:

* Important notifications first
* Efficient ranking
* Better user experience

---

## Conclusion

The notification system uses:

* REST APIs
* WebSocket
* PostgreSQL
* Redis Cache
* Kafka
* Priority Ranking

This design scales efficiently for millions of notifications.
