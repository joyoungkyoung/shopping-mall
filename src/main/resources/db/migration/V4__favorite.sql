SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `shopping-mall`.`favorite`;

-- 좋아요 정보
CREATE TABLE `shopping-mall`.`favorite` (
	id int auto_increment NOT NULL,
	product_id int NOT NULL,
	user_id int NOT NULL,
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`favorite` ADD CONSTRAINT favorite_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`favorite` ADD CONSTRAINT favorite_FK2 FOREIGN KEY (user_id) REFERENCES `shopping-mall`.`user`(id);

SET foreign_key_checks = 1;