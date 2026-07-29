-- Seed data only. Schema is created by Hibernate (spring.jpa.hibernate.ddl-auto).
-- Requires spring.jpa.defer-datasource-initialization=true so this runs after DDL.

INSERT INTO users (id, name, role, email) VALUES (101, 'John Doe', 'USER', 'john.doe@example.com');
INSERT INTO users (id, name, role, email) VALUES (102, 'Jane Smith', 'ADMIN', 'jane.smith@example.com');
INSERT INTO users (id, name, role, email) VALUES (103, 'Peter Jones', 'GUEST', 'peter.jones@example.com');
