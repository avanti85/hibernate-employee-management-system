# Hibernate Employee Management System

A console-based Employee Management System developed using Java and Hibernate ORM.
This project demonstrates clean Hibernate CRUD operations with real-world features
like audit logging and designation-based salary updates.

---

## 🚀 Features

- Add, update, delete, and view employees
- Department and Employee mapping (Many-to-One)
- Search employee by name (case-insensitive)
- Designation-based salary increment logic
- Employee salary change audit logging with timestamp
- JPQL queries with JOIN FETCH to avoid N+1 problem

---

## 🛠 Tech Stack

- Java
- Hibernate (JPA)
- MySQL
- Maven

---

## 🧱 Project Structure
src/main/java
├── entities
│ ├── EmployeeDTO.java
│ ├── DepartmentDTO.java
│ └── EmployeeAudit.java
├── dao
│ └── EmployeeDAO.java
├── service
│ └── EmployeeService.java
└── main
└── EmployeeMain.java

## ⭐ Special Highlights

- Implements audit trail for salary changes
- Follows layered architecture (Entity → DAO → Service)
- Business logic separated from database logic
- Uses JPQL instead of native SQL
