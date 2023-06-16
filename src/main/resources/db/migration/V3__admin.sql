DROP TABLE IF EXISTS `shopping-mall`.`admin`;

CREATE TABLE `shopping-mall`.`admin` (
	id int auto_increment NOT NULL,
	username varchar(20) NOT NULL COMMENT '어드민 아이디',
	`password` varchar(512) NULL,
    nickname varchar(100) NOT NULL COMMENT '어드민 이름',
	authority_code varchar(10) NOT NULL COMMENT '권한 코드',
	refresh_token varchar(512) NULL COMMENT '리프레시 토큰',
	create_date datetime NOT NULL,
	update_date datetime NULL,
	delete_date datetime NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `shopping-mall`.`authority` DROP creatable_code;
ALTER TABLE `shopping-mall`.`authority` ADD creatable_code varchar(200) NULL;

update `shopping-mall`.`authority` set creatable_code = 'STAFF|VIEWER' where code = 'OWNER';
update `shopping-mall`.`authority` set creatable_code = 'VIEWER' where code = 'STAFF';

insert into `shopping-mall`.`admin` (username, `password`, nickname, authority_code, create_date) values ('admin_owner', 'admin', 'admin_owner', 'OWNER', now())