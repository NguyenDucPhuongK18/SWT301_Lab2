drop database if exists swt301_lab2;
create database if not exists swt301_lab2;
use swt301_lab2;

CREATE TABLE `Category` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL
);

CREATE TABLE `Order_Details` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `price` DOUBLE NOT NULL,
    `quantity` INT NOT NULL,
    `discount` DOUBLE DEFAULT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL
);

CREATE TABLE `Orders` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,
    `price` DOUBLE NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `address` VARCHAR(200) NOT NULL,
    `note` VARCHAR(1000) DEFAULT NULL,
    `status` INT DEFAULT NULL,
    `total_discount` DOUBLE DEFAULT NULL,
    `total_quantity` INT DEFAULT NULL,
    `total_price` DOUBLE DEFAULT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL
);

CREATE TABLE `Product` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `category_id` INT DEFAULT NULL,
    `name` VARCHAR(250) NOT NULL,
    `price` DOUBLE NOT NULL,
    `discount` DOUBLE DEFAULT NULL,
    `image` VARCHAR(500) DEFAULT NULL,
    `description` TEXT DEFAULT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    `total_rating` INT DEFAULT NULL,
    `total_starts` INT DEFAULT NULL,
    `status` INT DEFAULT NULL
);

CREATE TABLE `Role` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `role_name` VARCHAR(50) NOT NULL,
    `description` TEXT DEFAULT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL
);

CREATE TABLE `User` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `full_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(150) DEFAULT NULL,
    `phone` VARCHAR(20) DEFAULT NULL,
    `address` VARCHAR(200) DEFAULT NULL,
    `username` VARCHAR(50) DEFAULT NULL,
    `password` VARCHAR(32) NOT NULL,
    `gender` VARCHAR(10) DEFAULT NULL,
    `avatar` VARCHAR(255) DEFAULT NULL,
    `role_id` INT DEFAULT NULL,
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL
);

CREATE TABLE `Rating` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    updated_id DATETIME,
    created_id DATETIME,
    number_starts INT NOT NULL
);

