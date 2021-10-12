DROP DATABASE IF EXISTS music_store_db;

CREATE DATABASE music_store_db;

USE music_store_db;

CREATE TABLE artists (
     artist_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     artist VARCHAR(100) NOT NULL
);

CREATE TABLE genres (
    genre_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(30) NOT NULL
);

CREATE TABLE albums (
    album_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(7) NOT NULL DEFAULT '',
    title VARCHAR(100) NOT NULL,
    artist_id INT NOT NULL,
    genre_id INT NOT NULL,
    year INT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(7, 2) NOT NULL DEFAULT '0.00',

    FOREIGN KEY (artist_id) REFERENCES artists (artist_id),
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

CREATE TABLE customers (
    customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL
);

CREATE TABLE credit_cards (
    card_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    credit_card_number VARCHAR(16) NOT NULL,
    credit_card_expiration_date VARCHAR(5) NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    customer_id INT NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
    ON DELETE CASCADE
);

CREATE TABLE orders (
    order_id VARCHAR(50) NOT NULL PRIMARY KEY,
    order_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    customer_id INT NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT FALSE,

    FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE orders_items (
    order_id VARCHAR(50) NOT NULL,
    album_id INT NOT NULL,
    quantity INT NOT NULL,

    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (album_id) REFERENCES albums (album_id)
);