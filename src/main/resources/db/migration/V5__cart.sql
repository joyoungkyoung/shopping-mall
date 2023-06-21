SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `shopping-mall`.`cart`;

-- 장바구니
CREATE TABLE `shopping-mall`.`cart` (
	id int auto_increment NOT NULL,
	user_id int NOT NULL,
	product_id int NOT NULL,
	product_option_id int NULL,
	cnt int NOT NULL,
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`cart` ADD CONSTRAINT cart_FK FOREIGN KEY (user_id) REFERENCES `shopping-mall`.`user`(id);
ALTER TABLE `shopping-mall`.`cart` ADD CONSTRAINT cart_FK2 FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`cart` ADD CONSTRAINT cart_FK3 FOREIGN KEY (product_option_id) REFERENCES `shopping-mall`.`product_option`(id);

ALTER TABLE `shopping-mall`.`cart` ADD UNIQUE (user_id, product_id);

SET foreign_key_checks = 1;