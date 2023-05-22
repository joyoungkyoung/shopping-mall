DROP TABLE IF EXISTS `shopping-mall`.`user`;

CREATE TABLE `shopping-mall`.`user` (
	id int auto_increment NOT NULL,
	`type` int NOT NULL COMMENT '계정타입(기본, 네이버, 페이스북, 카카오)',
	email varchar(100) NOT NULL,
	nickname varchar(100) NOT NULL,
	password varchar(512) NULL,
	user_level_id int NOT NULL COMMENT '회원등급 코드',
	address_id int NULL COMMENT '회원 배송지 주소',
	phone varchar(100) NULL,
	status int NOT NULL COMMENT '계정상태(정상, 정지, 삭제)',
	is_email_alert_confirm boolean NOT NULL COMMENT '광고 및 메일수신동의 여부',
	`point` int DEFAULT 0 NOT NULL COMMENT '마일리지 포인트',
	create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `shopping-mall`.`level`;

CREATE TABLE `shopping-mall`.`level` (
    id varchar(2) NOT NULL,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `shopping-mall`.`level` (id, name) VALUES('01', 'Family');
INSERT INTO `shopping-mall`.`level` (id, name) VALUES('02', 'Gold');
INSERT INTO `shopping-mall`.`level` (id, name) VALUES('03', 'VIP');
INSERT INTO `shopping-mall`.`level` (id, name) VALUES('04', 'VVIP');


DROP TABLE IF EXISTS `shopping-mall`.`address`;

CREATE TABLE `shopping-mall`.`address` (
    id int auto_increment NOT NULL,
    zip_code varchar(10) NOT NULL COMMENT '우편번호',
    address_main varchar(512) NOT NULL COMMENT '주소',
    address_sub varchar(512) NOT NULL COMMENT '상세주소',
    create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

