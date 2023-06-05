DROP TABLE IF EXISTS `shopping-mall`.`user`;

CREATE TABLE `shopping-mall`.`user` (
	id int auto_increment NOT NULL,
	`type` int NOT NULL COMMENT '계정타입(기본, 네이버, 페이스북, 카카오)',
	email varchar(100) NOT NULL,
	nickname varchar(100) NOT NULL,
	password varchar(512) NULL,
	authority_code varchar(10) NOT NULL COMMENT '권한 코드',
	address_id int NULL COMMENT '회원 배송지 주소',
	phone varchar(100) NULL,
	status int NOT NULL COMMENT '계정상태(정상, 정지, 삭제)',
	is_email_alert_confirm boolean NOT NULL COMMENT '광고 및 메일수신동의 여부',
	`point` int DEFAULT 0 NOT NULL COMMENT '마일리지 포인트',
	refresh_token varchar(512) NULL COMMENT '리프레시 토큰',
	create_date datetime NOT NULL,
	update_date datetime NULL,
	delete_date datetime NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `shopping-mall`.`authority`;

CREATE TABLE `shopping-mall`.`authority` (
    code varchar(10) NOT NULL,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (code)
);

INSERT INTO `shopping-mall`.`authority` (code, name) VALUES('OWNER', '시스템 관리자 권한');
INSERT INTO `shopping-mall`.`authority` (code, name) VALUES('STAFF', '일반 관리자 권한');
INSERT INTO `shopping-mall`.`authority` (code, name) VALUES('VIEWER', '어드민 페이지 보기 권한');
INSERT INTO `shopping-mall`.`authority` (code, name) VALUES('CUSTOMER', '고객');


DROP TABLE IF EXISTS `shopping-mall`.`address`;

CREATE TABLE `shopping-mall`.`address` (
    id int auto_increment NOT NULL,
    zip_code varchar(10) NOT NULL COMMENT '우편번호',
    address_main varchar(512) NOT NULL COMMENT '주소',
    address_sub varchar(512) NOT NULL COMMENT '상세주소',
    create_date datetime NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`user` ADD CONSTRAINT user_FK FOREIGN KEY (authority_code) REFERENCES `shopping-mall`.`authority`(code);

