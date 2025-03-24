# Employee Records Report System

A REST API for generating detailed multi-section PDF reports for employee records using JasperReports.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Report Structure](#report-structure)
- [Exception Handling](#exception-handling)

## Features
- Generate detailed employee record reports in PDF format
- Main report with multiple subreports (Job History, Performance Evaluations, Salary Trends)
- Company-wide department summary with charts
- Conditional formatting for high salaries and poor performance
- Filter reports by department and date range
- REST API for easy integration
- Error handling for missing data, incorrect parameters, and database failures

## Requirements
- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- Spring Boot 2.7.5

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/employee-report-system.git
cd employee-report-system

### 2. Configure Database
-Create a PostgreSQL database named employee_reports:
psql -U postgres
CREATE DATABASE employee_reports;

-Update the database configuration in src/main/resources/application.properties if needed:
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_reports
spring.datasource.username=postgres
spring.datasource.password=postgres


### 3. Build the application
mvn clean install

## 4.Run the application
mvn spring-boot:run
or
java -jar target/employee-report-system-0.0.1-SNAPSHOT.jar

-The application will start on port 8080 by default.