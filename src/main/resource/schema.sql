-- Employee table
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    employee_id VARCHAR(10) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    department VARCHAR(50) NOT NULL,
    current_salary NUMERIC(10, 2) NOT NULL,
    hire_date DATE NOT NULL
);

-- Job History table
CREATE TABLE job_history (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employees(id),
    position VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary NUMERIC(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    is_promotion BOOLEAN DEFAULT FALSE
);

-- Performance Evaluation table
CREATE TABLE performance_evaluations (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employees(id),
    evaluation_year INT NOT NULL,
    rating NUMERIC(3, 1) NOT NULL,
    feedback TEXT,
    evaluation_date DATE NOT NULL,
    CONSTRAINT unique_employee_year UNIQUE (employee_id, evaluation_year)
);

-- Department Summary View (for easier reporting)
CREATE VIEW department_summary AS
SELECT 
    department, 
    COUNT(*) as employee_count
FROM 
    employees
GROUP BY 
    department;
