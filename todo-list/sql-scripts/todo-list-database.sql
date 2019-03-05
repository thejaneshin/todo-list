CREATE DATABASE  IF NOT EXISTS `todo-database`;
USE `todo-database`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `todo_list`;

CREATE TABLE `todo_list` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `title` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `todo_item`;

CREATE TABLE `todo_item` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `description` TEXT DEFAULT NULL,
 `deadline` TIMESTAMP DEFAULT NULL,
 `status` tinyint(1) DEFAULT NULL,
 `todo_list_id` int(11) DEFAULT NULL,
 
 PRIMARY KEY (`id`),
 
 KEY `FK_LIST_idx` (`todo_list_id`),
 CONSTRAINT `FK_LIST`
 FOREIGN KEY (`todo_list_id`)
 REFERENCES `todo_list` (`id`)
 ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;