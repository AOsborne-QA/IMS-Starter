INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Shadow', 'Cat');
INSERT INTO `ims`.`items` (`item_name`, `item_price`) VALUES ('Mouse', 1.99);
INSERT INTO `ims`.`items` (`item_name`, `item_price`) VALUES ('Treats', 6.99);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (2);
INSERT INTO `ims`.`orders_items` (`fk_order_id`, `fk_item_id`, `unit_price`, `quantity`, `order_cost`) VALUES (1, 1, 1.99, 10, 11.99);