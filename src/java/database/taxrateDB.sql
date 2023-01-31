/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ohiow
 * Created: Jan 28, 2023
 */

DROP SCHEMA IF EXISTS `taxratedb`;
CREATE SCHEMA IF NOT EXISTS `taxratedb` DEFAULT CHARACTER SET latin1;
USE `taxratedb`;



CREATE TABLE IF NOT EXISTS `taxratedb`.`role` (
`role_id` INT(1) NOT NULL,
`role_name` VARCHAR(20) NOT NULL, 
PRIMARY KEY(`ROLE_ID`)
);

CREATE TABLE IF NOT EXISTS `taxratedb`.`user` (
	`user_id` INT(3) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(30) NOT NULL,
	`password` VARCHAR(30) NOT NULL,
	`role_id` INT(1) NOT NULL,
	PRIMARY KEY(`user_id`)
);

CREATE TABLE IF NOT EXISTS `taxratedb`.`taxrateca` (
	`taxrate_id` INT(10) NOT NULL AUTO_INCREMENT,
	`gst` DOUBLE NOT NULL,
	`pst` DOUBLE NOT NULL,
	`hst` DOUBLE NOT NULL,
	`location_code` VARCHAR(7) NOT NULL ,
	PRIMARY KEY (`taxrate_id`)
);

CREATE TABLE IF NOT EXISTS `taxratedb`.`location` (
	`location_code` VARCHAR(10) NOT NULL,
	`country` VARCHAR(30) NOT NULL,
	`region` VARCHAR(30) NOT NULL,
	PRIMARY KEY(`location_code`)
);
	


ALTER TABLE `user` ADD CONSTRAINT `fk_user_role_id` FOREIGN KEY(`role_id`) REFERENCES `taxratedb`.`role` (`role_id`);
--ALTER TABLE `user` ADD CONSTRAINT `ck_user_pass` CHECK (`password` REGEXP '^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{6,}$');
ALTER TABLE `taxrateca` ADD CONSTRAINT `fk_taxrateca_location_code` FOREIGN KEY(`location_code`) REFERENCES `taxratedb`.`location` (`location_code`);



INSERT INTO `role` VALUES (1, 'Admin' );
INSERT INTO `role` VALUES (2, 'User' );

INSERT INTO `user` (`username`,`password`,`role_id`) VALUES ('AdminAlpha','alpha', 1); 
INSERT INTO `user` (`username`,`password`,`role_id`) VALUES ('AdminBeta','beta', 1); 
INSERT INTO `user` (`username`,`password`,`role_id`) VALUES ('AdminOmega','omega', 1); 

INSERT INTO `location` VALUES ('V3G','CAN','BC');
INSERT INTO `location` VALUES ('T3J','CAN','AB');
INSERT INTO `location` VALUES ('S6V','CAN','SK');
INSERT INTO `location` VALUES ('R2P','CAN','MB');
INSERT INTO `location` VALUES ('L7L','CAN','ONT');
INSERT INTO `location` VALUES ('G2L','CAN','QUE');
INSERT INTO `location` VALUES ('A1G','CAN','NWFL');
INSERT INTO `location` VALUES ('E7P','CAN','NB');
INSERT INTO `location` VALUES ('B1Y','CAN','NS');
INSERT INTO `location` VALUES ('C1C','CAN','PEI');
INSERT INTO `location` VALUES ('X0A','CAN','NUV');
INSERT INTO `location` VALUES ('X0E','CAN','NWT');
INSERT INTO `location` VALUES ('Y0B','CAN','YK');

INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (5,7,0,'V3G');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (5,0,0,'T3J');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (5,6,0,'S6V');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (5,7,0,'R2P');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,0,13,'L7L');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (5,9.975,0,'G2L');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,0,15,'A1G');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,0,15,'E7P');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,0,15,'B1Y');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,0,15,'C1C');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,5,0,'X0A');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,5,0,'X0E');
INSERT INTO `taxrateca` (`gst`,`pst`,`hst`,`location_code`) VALUES (0,5,0,'Y0B');
	
