-- src/main/resources/schema.sql
-- This file defines the initial schema. JPA's ddl-auto will handle most of this,
-- but explicitly defining it here for clarity and in case ddl-auto is set to 'none' later.

CREATE TABLE IF NOT EXISTS dept (
    deptno INT PRIMARY KEY,
    deptname VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    deptno INT, -- New column for department
    CONSTRAINT fk_users_dept FOREIGN KEY (deptno) REFERENCES dept(deptno)
);