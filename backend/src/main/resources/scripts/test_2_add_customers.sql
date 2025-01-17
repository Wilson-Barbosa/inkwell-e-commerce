INSERT INTO Person(email, password, created_at, role_id)
VALUES
('jonny_carlson@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 2),
('natalia@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 2),
('lucilene@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 2);

INSERT INTO Customer(person_id, first_name, last_name, dob)
VALUES
(1, 'John', 'Carl', '1999-09-05'),
(2, 'Natalia', 'Mackenzy', '1992-12-14'),
(3, 'Peter', null, null);
