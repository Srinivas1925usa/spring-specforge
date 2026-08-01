-- src/main/resources/import.sql
-- SQL commands to populate data for H2 in-memory database.
-- These commands will be executed on startup if spring.sql.init.mode is 'always'
-- and spring.jpa.hibernate.ddl-auto is suitable (e.g., 'create', 'create-drop', or 'update' for existing tables).

-- Insert sample departments (include location + salary required by entity schema)
INSERT INTO dept (deptno, deptname, location, salary) VALUES (10, 'Engineering', 'San Francisco', 10000.00);
INSERT INTO dept (deptno, deptname, location, salary) VALUES (20, 'HR', 'New York', 10000.00);
INSERT INTO dept (deptno, deptname, location, salary) VALUES (30, 'Marketing', 'Chicago', 10000.00);

-- Insert sample users (ensure IDs match existing expectations for updates)
INSERT INTO users (id, name, role, email, deptno) VALUES (101, 'John Doe', 'USER', 'john.doe@example.com', 10);
INSERT INTO users (id, name, role, email, deptno) VALUES (102, 'Jane Smith', 'ADMIN', 'jane.smith@example.com', 20);
INSERT INTO users (id, name, role, email, deptno) VALUES (103, 'Peter Jones', 'GUEST', 'peter.jones@example.com', 30);
INSERT INTO users (id, name, role, email, deptno) VALUES (104, 'Alice Wonderland', 'USER', 'alice.w@example.com', NULL); -- User without a department

-- Sample addresses linked One-to-One to users 101, 102, 103 (user 104 intentionally has no address)
INSERT INTO address (street, city, state, zip_code, country, user_id) VALUES ('123 Tech Park', 'San Francisco', 'CA', '94105', 'USA', 101);
INSERT INTO address (street, city, state, zip_code, country, user_id) VALUES ('456 HR Avenue', 'New York', 'NY', '10001', 'USA', 102);
INSERT INTO address (street, city, state, zip_code, country, user_id) VALUES ('789 Market Street', 'Chicago', 'IL', '60601', 'USA', 103);
