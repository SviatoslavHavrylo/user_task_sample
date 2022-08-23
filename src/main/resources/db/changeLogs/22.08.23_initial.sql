--liquibase formatted sql

--changeset sviatoslav:init userTask db
DROP TABLE IF EXISTS `task`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `task`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `date_time`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `user_id`     bigint       DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_task_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    INDEX `user_id_idx` (`user_id`),
    INDEX `task_id_user_id_idx` (`id`, `user_id`)
);


