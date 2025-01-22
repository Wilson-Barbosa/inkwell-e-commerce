INSERT INTO Person(email, password, created_at, role_id)
VALUES
('jonny_carlson@gmail.com', '$2a$10$NTQ2FSQucldNu677cvc4uOwMo88a8N8e.f9QAehdJ6M/DSwSBynFK', '2024-09-03T15:30:45.123456789Z', 2),
('natalia@gmail.com', '$2a$10$XV1lF2ZxQDF2LFrPLwgdvOXmQD4wa5j3yNBs.WYYuQM3wnhDPSDh2', '2024-09-03T15:30:45.123456789Z', 2),
('peter94@gmail.com', '$2a$10$zzl5NjO/qtieTUm23B0Z2.MI8Cj0lyIcf5A2nznE0ouLqLczz.cPu', '2024-09-03T15:30:45.123456789Z', 2);

INSERT INTO Customer(person_id, first_name, last_name, dob)
VALUES
(1, 'John', 'Carl', '1999-09-05'),
(2, 'Natalia', 'Mackenzy', '1992-12-14'),
(3, 'Peter', null, null);
