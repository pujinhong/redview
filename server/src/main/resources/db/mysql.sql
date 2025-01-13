CREATE DATABASE `red-view` /*!40100 COLLATE 'utf8mb4_general_ci' */

CREATE TABLE IF NOT EXISTS `t_datasource` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(255),
    `name` VARCHAR(255),
    `remark` VARCHAR(255),
    `db_type` VARCHAR(255),
    `db_driver` VARCHAR(255),
    `db_url` VARCHAR(1000),
    `db_instance` VARCHAR(255),
    `db_username` VARCHAR(255),
    `db_password` VARCHAR(255),
    `db_port` INT,
    `db_host` VARCHAR(255),
    `del_flag` INT DEFAULT 0,
    `online` INT DEFAULT 1,
    `create_time` DATETIME,
    `create_by` VARCHAR(255),
    `update_time` DATETIME,
    `update_by` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_bento_sql` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `title` VARCHAR(255) NULL,
    `db_code` VARCHAR(255) NULL,
    `statement` TEXT NULL,
    `create_by` VARCHAR(255) NULL,
    `create_time` DATETIME NULL,
    `update_by` VARCHAR(255) NULL,
    `update_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_bento_json` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `title` VARCHAR(255) NULL,
    `json` TEXT NULL,
    `create_by` VARCHAR(255) NULL,
    `create_time` DATETIME NULL,
    `update_by` VARCHAR(255) NULL,
    `update_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);