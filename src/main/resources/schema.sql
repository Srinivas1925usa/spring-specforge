-- src/main/resources/schema.sql
-- This file defines the initial schema. JPA's ddl-auto will handle most of this,
-- but explicitly defining it here for clarity and in case ddl-auto is set to 'none' later.

CREATE TABLE IF NOT EXISTS dept (
    deptno INT PRIMARY KEY,
    deptname VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL DEFAULT 'Unknown', -- New column: location with default value
    salary DECIMAL(10, 2) NOT NULL DEFAULT 10000.00 -- New column: salary with default value
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    deptno INT, -- New column for department
    CONSTRAINT fk_users_dept FOREIGN KEY (deptno) REFERENCES dept(deptno)
);

-- One-to-One address table: FK user_id → users.id with UNIQUE constraint
CREATE TABLE IF NOT EXISTS address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(50),
    country VARCHAR(255),
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES users(id)
);