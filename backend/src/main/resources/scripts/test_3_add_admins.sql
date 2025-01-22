INSERT INTO Person(email, password, created_at, role_id)
VALUES
('leibniz@gmail.com', '$2a$10$BFP7jmI39rI5eQbvx2boaOVPfm/v6tr5io5/O9FAVO/Vjx0O1eb42', '2024-09-03T15:30:45.123456789Z', 1),
('carnot@gmail.com', '$2a$10$UHx1FYQYkEns3wphQQ2mI.iOX8eZGhk6M65dUgtA/GKQ44EhppaXS', '2024-09-03T15:30:45.123456789Z', 1),
('pascal@gmail.com', '$2a$10$eCWQzfB.NAFaLQsrTji6VexpPf.UJTKGTqDgrGCAAiDZBoMbkYQlG', '2024-09-03T15:30:45.123456789Z', 1);

INSERT INTO Admin(person_id, name)
VALUES
(4, 'Gottfried Leibniz'),
(5, 'Sadi Carnot'),
(6, 'Blaise Pascal');
