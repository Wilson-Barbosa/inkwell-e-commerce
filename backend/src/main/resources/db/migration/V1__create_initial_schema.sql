-- Creates Role table
CREATE TABLE Role(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- Creates Person table
CREATE TABLE Person(
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    role_id INTEGER REFERENCES Role(id)
);

-- Creates Admin table
CREATE TABLE Admin(
    person_id BIGINT PRIMARY KEY REFERENCES Person(id)
);

-- Creates Customer table
CREATE TABLE Customer(
    person_id BIGINT PRIMARY KEY REFERENCES Person(id),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    dob DATE
);

-- Creates Product table
CREATE TABLE Product(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    -- defines an array type to store all the images for a particular product
    images_list TEXT[], 
    price NUMERIC(10,2) NOT NULL,
    discount NUMERIC(4,2) DEFAULT 00.00,
    created_at TIMESTAMP NOT NULL,
    rating NUMERIC(2,1) NOT NULL DEFAULT 0.0,
    created_by BIGINT REFERENCES Admin(person_id)
);

-- Creates Book table
CREATE TABLE Book(
    author VARCHAR(150) NOT NULL,
    number_of_pages INTEGER NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    edition INTEGER NOT NULL,
    product_id BIGINT PRIMARY KEY REFERENCES Product(id)
);

-- Creates Category table
CREATE TABLE Category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Creates ProductCategory table
CREATE TABLE Product_category(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT REFERENCES Product(id),
    category_id INTEGER REFERENCES Category(id)
);

-- Creates Review table
CREATE TABLE Review(
    id BIGSERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_updated TIMESTAMP,
    rating INTEGER NOT NULL,
    product_id BIGINT REFERENCES Product(id),
    author INTEGER REFERENCES Customer(person_id)
);

-- Creates ShoppingCart table
CREATE TABLE Shopping_cart(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    last_updated TIMESTAMP NOT NULL,
    customer_id BIGINT REFERENCES Customer(person_id)
);

-- Creates ProductCart table
CREATE TABLE Product_cart(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    shoppingCart_id BIGINT REFERENCES Shopping_cart(id),
    product_id BIGINT REFERENCES Product(id)
);

-- Creates Order table
CREATE TABLE Customer_order(
    id BIGSERIAL PRIMARY KEY,
    total_value NUMERIC NOT NULL,
    order_status smallint NOT NULL,
    created_at TIMESTAMP NOT NULL,
    customer_id BIGINT REFERENCES Customer(person_id)
);

-- Creates OrderItem table
CREATE TABLE Order_item(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    discount NUMERIC(4,2) NOT NULL,
    order_id BIGINT REFERENCES Customer_order(id),
    product_id BIGINT REFERENCES Product(id)
);

CREATE TABLE Bundle(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT REFERENCES Customer(person_id)
);

CREATE TABLE Bundle_item(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT REFERENCES Product(id),
    bundle_id BIGINT REFERENCES Bundle(id)
);