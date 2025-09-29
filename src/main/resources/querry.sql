create database warehouse;

use warehouse;

CREATE TABLE stock (id INT NOT NULL AUTO_INCREMENT, ingredient VARCHAR(50) NOT NULL, quantity INT NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB;

SELECT *
FROM stock;

DELETE
From stock;