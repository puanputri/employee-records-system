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
