CREATE DATABASE IF NOT EXISTS dbdealership;
USE dbdealership;

CREATE TABLE Customer (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE Car (
	id INT PRIMARY KEY AUTO_INCREMENT,
    plate_number VARCHAR(10) NOT NULL UNIQUE,
    brand VARCHAR(15) NOT NULL,
    model VARCHAR(15) NOT NULL
);

CREATE TABLE Sale (
	id INT PRIMARY KEY AUTO_INCREMENT,
    type ENUM('new', 'resale') NOT NULL DEFAULT 'new',
    date_sale DATETIME NOT NULL,
    cost INT NOT NULL CHECK (cost > 0),
    customer_id INT NOT NULL,
    car_plate_number VARCHAR(10) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(id) ON UPDATE CASCADE,
    FOREIGN KEY (car_plate_number) REFERENCES Car(plate_number) ON UPDATE CASCADE
);