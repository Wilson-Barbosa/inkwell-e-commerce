CREATE TABLE person(
    id SERIAL PRIMARY KEY,
    name varchar(140) UNIQUE NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    photo varchar(255)
);