DROP SCHEMA imstest;
CREATE SCHEMA IF NOT EXISTS `imstest`;

use imstest ;

CREATE TABLE IF NOT EXISTS customers (
    id INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) DEFAULT NULL,
    surname VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
    id INT(11) NOT NULL AUTO_INCREMENT,
    itemName VARCHAR(40) DEFAULT NULL,
    price DOUBLE DEFAULT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS orders (
    id INT(11) NOT NULL AUTO_INCREMENT,
    customer_id int DEFAULT NULL unique,
    date_ordered VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`)
);


CREATE TABLE IF NOT EXISTS order_items (
    OrderItems_id INT(11) NOT NULL AUTO_INCREMENT,
    Order_id INT NOT NULL,
    Item_id INT NOT NULL,
    PRIMARY KEY (OrderItems_id),
    FOREIGN KEY (Order_id) REFERENCES orders(id),
    FOREIGN KEY (Item_id) REFERENCES items(id)
);