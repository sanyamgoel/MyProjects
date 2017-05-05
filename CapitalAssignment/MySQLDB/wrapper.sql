CREATE DATABASE IF NOT EXISTS capital;
USE capital;
DROP TABLE IF EXISTS users;
CREATE TABLE `users`(
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
)