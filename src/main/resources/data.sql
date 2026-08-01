-- src/main/resources/data.sql
-- Initial data for the 'dept' table
INSERT INTO dept (deptno, deptname, location, salary) VALUES (10, 'Development', 'New York', 10000.00) ON CONFLICT(deptno) DO UPDATE SET deptname = EXCLUDED.deptname, location = EXCLUDED.location, salary = EXCLUDED.salary;
INSERT INTO dept (deptno, deptname, location, salary) VALUES (20, 'Human Resources', 'London', 10000.00) ON CONFLICT(deptno) DO UPDATE SET deptname = EXCLUDED.deptname, location = EXCLUDED.location, salary = EXCLUDED.salary;
INSERT INTO dept (deptno, deptname, location, salary) VALUES (30, 'Sales', 'Paris', 10000.00) ON CONFLICT(deptno) DO UPDATE SET deptname = EXCLUDED.deptname, location = EXCLUDED.location, salary = EXCLUDED.salary;

-- Initial data for the 'users' table
INSERT INTO users (id, name, role, email, deptno) VALUES (1, 'Alice Smith', 'ADMIN', 'alice.smith@example.com', 10) ON CONFLICT(id) DO UPDATE SET name = EXCLUDED.name, role = EXCLUDED.role, email = EXCLUDED.email, deptno = EXCLUDED.deptno;
INSERT INTO users (id, name, role, email, deptno) VALUES (2, 'Bob Johnson', 'USER', 'bob.johnson@example.com', 20) ON CONFLICT(id) DO UPDATE SET name = EXCLUDED.name, role = EXCLUDED.role, email = EXCLUDED.email, deptno = EXCLUDED.deptno;
INSERT INTO users (id, name, role, email, deptno) VALUES (3, 'Charlie Brown', 'USER', 'charlie.brown@example.com', 10) ON CONFLICT(id) DO UPDATE SET name = EXCLUDED.name, role = EXCLUDED.role, email = EXCLUDED.email, deptno = EXCLUDED.deptno;
INSERT INTO users (id, name, role, email, deptno) VALUES (4, 'Diana Prince', 'ADMIN', 'diana.prince@example.com', 30) ON CONFLICT(id) DO UPDATE SET name = EXCLUDED.name, role = EXCLUDED.role, email = EXCLUDED.email, deptno = EXCLUDED.deptno;
INSERT INTO users (id, name, role, email, deptno) VALUES (5, 'Eve Adams', 'USER', 'eve.adams@example.com', NULL) ON CONFLICT(id) DO UPDATE SET name = EXCLUDED.name, role = EXCLUDED.role, email = EXCLUDED.email, deptno = EXCLUDED.deptno;

-- Initial data for the 'address' table
INSERT INTO address (id, street, city, state, zip_code, country, user_id) VALUES (101, '123 Main St', 'Anytown', 'CA', '90210', 'USA', 1) ON CONFLICT(id) DO UPDATE SET street = EXCLUDED.street, city = EXCLUDED.city, state = EXCLUDED.state, zip_code = EXCLUDED.zip_code, country = EXCLUDED.country, user_id = EXCLUDED.user_id;
INSERT INTO address (id, street, city, state, zip_code, country, user_id) VALUES (102, '456 Oak Ave', 'Sometown', 'NY', '10001', 'USA', 2) ON CONFLICT(id) DO UPDATE SET street = EXCLUDED.street, city = EXCLUDED.city, state = EXCLUDED.state, zip_code = EXCLUDED.zip_code, country = EXCLUDED.country, user_id = EXCLUDED.user_id;

-- Explicitly update existing dept records to ensure salary and location are set for older data
UPDATE dept SET salary = 10000.00 WHERE salary IS NULL;
UPDATE dept SET location = 'Unknown' WHERE location IS NULL;