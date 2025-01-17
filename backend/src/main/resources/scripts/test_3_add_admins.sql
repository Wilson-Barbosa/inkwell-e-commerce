INSERT INTO Person(email, password, created_at, role_id)
VALUES
('leibniz@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 1),
('carnot@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 1),
('pascal@gmail.com', '123', '2024-09-03T15:30:45.123456789Z', 1);

INSERT INTO Admin(person_id, name)
VALUES
(4, 'Gottfried Leibniz'),
(5, 'Sadi Carnot'),
(6, 'Blaise Pascal');
