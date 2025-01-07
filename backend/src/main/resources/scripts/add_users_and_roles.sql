INSERT INTO Role(role) VALUES (1), (2);

INSERT INTO Person(id, email, password, created_at, role_id)
VALUES
(1, 'wilson@gmail.com', '123456', '2025-01-06T23:47:15.044180520Z', 1),
(2, 'newton@gmail.com', '123456', '2025-01-06T23:47:15.044180520Z', 2),
(3, 'leibniz@gmail.com', '123456', '2025-01-06T23:47:15.044180520Z', 1);