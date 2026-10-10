# Hospital-Management-System
Hospital Management System (HMS) — Backend API
A modular, enterprise-grade backend service built with Spring Boot to streamline clinical administration, patient admissions, and appointment scheduling workflows.

--📌 Project Overview
Inspired by real-world, clinical-grade hospital portals observed during outpatient consultations, this system replaces fragmented paperwork with a structured digital record. The platform coordinates patient registration, physician profiling by medical specializations, and real-time appointment scheduling with follow-up tracking.
The architecture emphasizes clear separation of concerns, data integrity across relational models, robust global exception handling, and domain-driven API contracts.

--✨ Features Implemented
Patient Lifecycle Management: Register, update, and fetch patient records, including clinical metadata and strongly typed demographic data (e.g., Enum-based Blood Group mappings).
Physician Specialization Catalog: Register medical practitioners mapped to distinct departments and clinical specialties.
Appointment Scheduling: Book, update, and manage consultation slots, linking patients to specific doctors with tracked arrival timestamps.
Relational Data Integrity: Normalized database schema utilizing JPA/Hibernate entity mappings (@ManyToOne, @ManyToMany) across doctors, patients, and scheduled visits.
Standardized API Contracts: Decoupled data transfer layers with dedicated Request/Response DTOs and semantic ResponseEntity status mappings.
Centralized Exception Handling: Custom exception hierarchies with @RestControllerAdvice delivering standard error envelopes.
Automated Testing: Unit test suite covering appointment booking business logic and edge cases.

--🛠️ Tech Stack & Architecture
Language & Runtime: Java (JDK 17+)
Framework: Spring Boot (Spring Web MVC, Spring Data JPA)
Database: Relational DB (MySQL / PostgreSQL) via Hibernate ORM
Testing: JUnit 5, Mockito
Build Tool: Maven

--Layered Architecture
Plaintext
Controller Layer (REST Endpoints & Validation)
       ↓
Service Layer (Domain Logic & Transaction Management)
       ↓
Repository Layer (Spring Data JPA / Derived Queries)
       ↓
Persistence Layer (Relational Database)

--🚀 In Progress
Authentication & Role-Based Access Control (RBAC): Implementing Spring Security 6 with custom SecurityFilterChain, JWT-based stateless authentication, and granular method security across Admin, Doctor, and Staff roles.
Expanded REST Endpoints: Additional controller handlers for batch doctor scheduling, appointment cancellations, and status lookups.

--🗺️ Roadmap & Future Enhancements
Infrastructure & Resource Tracking: Inventory management for clinical equipment, bed availability, operation theater (OT) allocations, and nurse/staff shift schedules.
Emergency Dispatch Module: High-priority triage workflow for emergency room admissions with immediate physician alerting.
Distributed Architecture: Transitioning core modules into event-driven microservices orchestrated with Apache Kafka for asynchronous notifications (SMS/Email alerts, appointment reminders).
Polyglot Persistence & Cloud Migration: Integrating MongoDB for unstructured medical telemetry/imaging records and containerizing for deployment on AWS (ECS, RDS, S3).

--⚙️ Getting Started
Prerequisites
JDK 17 or higher
Maven 3.8+
MySQL / PostgreSQL server

--Local Setup
Clone the repository:
Bash
git clone https://github.com/<your-username>/hospital-management-system.git
cd hospital-management-system
Configure your database credentials in src/main/resources/application.properties:

--Properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=auto

--Build and run:
Bash
mvn clean install
mvn spring-boot:run
Execute unit test suite:
Bash
mvn test
