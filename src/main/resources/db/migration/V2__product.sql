SET foreign_key_checks = 0;

-- 참조 테이블 우선 삭제
DROP TABLE IF EXISTS `shopping-mall`.`image`;
DROP TABLE IF EXISTS `shopping-mall`.`category`;
DROP TABLE IF EXISTS `shopping-mall`.`discount`;
DROP TABLE IF EXISTS `shopping-mall`.`product`;
DROP TABLE IF EXISTS `shopping-mall`.`product_option`;
DROP TABLE IF EXISTS `shopping-mall`.`product_metadata`;
DROP TABLE IF EXISTS `shopping-mall`.`product_image`;
DROP TABLE IF EXISTS `shopping-mall`.`product_review`;
DROP TABLE IF EXISTS `shopping-mall`.`product_qna`;


-- 이미지
CREATE TABLE `shopping-mall`.`image` (
	id int auto_increment NOT NULL,
	`url` varchar(1000) NOT NULL,
	`name` varchar(1000) NOT NULL,
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

-- 카테고리
CREATE TABLE `shopping-mall`.`category` (
	id int auto_increment NOT NULL,
	parent_category_id int NULL COMMENT '상위 카테고리',
	`name` varchar(100) NOT NULL COMMENT '카테고리 명',
	`order` int NOT NULL COMMENT '카테고리 순서',
	create_date datetime NOT NULL,
	update_date datetime NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`category` ADD CONSTRAINT category_FK FOREIGN KEY (parent_category_id) REFERENCES `shopping-mall`.`category`(id);

-- 할인
CREATE TABLE `shopping-mall`.`discount` (
	id int auto_increment NOT NULL,
	is_use boolean NOT NULL COMMENT '사용 여부',
	`type` int NOT NULL COMMENT '할인유형(기간할인/재구매할인/회원할인/배송비할인)',
	`name` varchar(100) NOT NULL COMMENT '할인명',
	`start_date` datetime NOT NULL COMMENT '할인기간(시작)',
	`end_date` datetime NOT NULL COMMENT '할인기간(종료)',
	area int NOT NULL COMMENT '할인적용범위(전체/상품/카테고리)',
	target_product_id int NULL COMMENT '할인적용범위가 상품일 경우 상품 아이디',
	target_category_id int NULL COMMENT '할인적용범위가 카테고리일 경우 카테고리 아이디',
	percent int NULL COMMENT '할인비율(%)',
	discount_price int NULL COMMENT '할인금액',
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

-- 상품
CREATE TABLE `shopping-mall`.`product` (
	id int auto_increment NOT NULL,
	category_id int NOT NULL,
	discount_id	int NOT NULL,
	`name` varchar(100) NOT NULL COMMENT '상품명',
	`description` longtext NULL,
	main_image_id int NOT NULL COMMENT '상품 메인 이미지 아이디',
	thumb_image_id int NOT NULL COMMENT '상품 썸네일 이미지 아이디',
	supply_price int NOT NULL,
	consumer_price int NOT NULL,
	product_price int NOT NULL,
	tax int NOT NULL COMMENT '과세/영세/면세 에 따라 다름. 디폴트 10%',
	margin int NOT NULL COMMENT '마진율',
	tag longtext NULL COMMENT '태그(검색용)',
	stock int DEFAULT 0 NOT NULL COMMENT '재고량',
	is_show boolean DEFAULT 0 NOT NULL COMMENT '표시여부',
	create_date datetime NOT NULL,
	update_date datetime NULL,
	delete_date datetime NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product` ADD CONSTRAINT product_FK FOREIGN KEY (category_id) REFERENCES `shopping-mall`.`category`(id);
ALTER TABLE `shopping-mall`.`product` ADD CONSTRAINT product_FK2 FOREIGN KEY (discount_id) REFERENCES `shopping-mall`.`discount`(id);
ALTER TABLE `shopping-mall`.`product` ADD CONSTRAINT product_FK3 FOREIGN KEY (main_image_id) REFERENCES `shopping-mall`.`image`(id);
ALTER TABLE `shopping-mall`.`product` ADD CONSTRAINT product_FK4 FOREIGN KEY (thumb_image_id) REFERENCES `shopping-mall`.`image`(id);

ALTER TABLE `shopping-mall`.`discount` ADD CONSTRAINT discount_FK FOREIGN KEY (target_product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`discount` ADD CONSTRAINT discount_FK2 FOREIGN KEY (target_category_id) REFERENCES `shopping-mall`.`category`(id);

-- 상품 옵션
CREATE TABLE `shopping-mall`.`product_option` (
	id int auto_increment NOT NULL,
	product_id int NOT NULL,
	opt_name varchar(100) NOT NULL COMMENT '옵션 명',
	opt_value varchar(100) NOT NULL COMMENT '옵션 값',
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product_option` ADD CONSTRAINT product_option_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);

-- 상품 메타데이터
CREATE TABLE `shopping-mall`.`product_metadata` (
	id int auto_increment NOT NULL,
	product_id int NOT NULL,
	field_name varchar(100) NOT NULL COMMENT '상품정보 테이블 셀 이름',
	field_value varchar(100) NOT NULL COMMENT '상품정보 테이블 셀 내용',
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product_metadata` ADD CONSTRAINT product_metadata_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);

-- 상품 이미지
CREATE TABLE `shopping-mall`.`product_image` (
	id int auto_increment NOT NULL,
	product_id int NOT NULL,
	image_id int NOT NULL,
	is_main boolean NOT NULL COMMENT '메인 이미지 여부',
	`order` int NOT NULL COMMENT '이미지 순서',
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product_image` ADD CONSTRAINT product_image_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`product_image` ADD CONSTRAINT product_image_FK2 FOREIGN KEY (image_id) REFERENCES `shopping-mall`.`image`(id);

-- 상품 리뷰
CREATE TABLE `shopping-mall`.`product_review` (
	id int auto_increment NOT NULL,
	user_id int NOT NULL,
	product_id int NOT NULL,
	image_id int NULL,
	score int NOT NULL COMMENT '별점(1~5)',
	body varchar(500) NOT NULL COMMENT '내용',
	create_date datetime NOT NULL,
	update_date datetime NULL,
	delete_date datetime NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product_review` ADD CONSTRAINT product_review_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`product_review` ADD CONSTRAINT product_review_FK2 FOREIGN KEY (image_id) REFERENCES `shopping-mall`.`image`(id);
ALTER TABLE `shopping-mall`.`product_review` ADD CONSTRAINT product_review_FK3 FOREIGN KEY (user_id) REFERENCES `shopping-mall`.`user`(id);


-- 상품 Q&A
CREATE TABLE `shopping-mall`.`product_qna` (
	id int auto_increment NOT NULL,
	user_id int NOT NULL,
	product_id int NOT NULL,
	question varchar(500) NOT NULL,
	answer varchar(500) NOT NULL,
	create_date datetime NOT NULL,
	update_date datetime NULL,
	answer_date datetime NULL,
	delete_date datetime NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`product_qna` ADD CONSTRAINT product_qna_FK FOREIGN KEY (product_id) REFERENCES `shopping-mall`.`product`(id);
ALTER TABLE `shopping-mall`.`product_qna` ADD CONSTRAINT product_qna_FK2 FOREIGN KEY (user_id) REFERENCES `shopping-mall`.`user`(id);

SET foreign_key_checks = 1;