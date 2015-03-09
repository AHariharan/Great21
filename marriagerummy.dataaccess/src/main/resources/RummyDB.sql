SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Rummy` ;
USE `Rummy` ;

-- -----------------------------------------------------
-- Table `Rummy`.`USER_ROLES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_ROLES` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_ROLES` (
  `USER_ID` BIGINT NOT NULL ,
  `GRANTED_ROLE` VARCHAR(150) NOT NULL ,
  PRIMARY KEY (`USER_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`USER_ACCOUNTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_ACCOUNTS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_ACCOUNTS` (
  `USER_ID` BIGINT NOT NULL AUTO_INCREMENT ,
  `EMAIL_ADDR` VARCHAR(100) NOT NULL ,
  `NICK_NAME` VARCHAR(45) NOT NULL ,
  `PASSWORD` VARCHAR(100) NOT NULL ,
  `ENABLED` TINYINT(1) NOT NULL ,
  `CREATED_DATE` DATETIME NOT NULL ,
  PRIMARY KEY (`USER_ID`, `EMAIL_ADDR`) ,
  UNIQUE INDEX `EMAIL_ADDR_UNIQUE` (`EMAIL_ADDR` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`RUMMY_STATS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`RUMMY_STATS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`RUMMY_STATS` (
  `USER_ID` BIGINT NOT NULL ,
  `CASH` DOUBLE NOT NULL ,
  `RATING` CHAR(5) NULL ,
  `WIN_PERCENT` INT NULL ,
  PRIMARY KEY (`USER_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`USER_AUDIT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_AUDIT` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_AUDIT` (
  `NICKNAME` VARCHAR(45) NOT NULL ,
  `LAST_LOGGEDIN_DATE` DATETIME NULL ,
  `LOG_INSTANCE_ID` BIGINT NOT NULL AUTO_INCREMENT ,
  `LAST_LOGGEDOUT_DATE` DATETIME NULL ,
  PRIMARY KEY (`LOG_INSTANCE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAME_PLAYERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAME_PLAYERS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAME_PLAYERS` (
  `GAME_INSTANCE_ID` VARCHAR(65) NOT NULL ,
  `NUM_OF_PLAYERS` INT NULL ,
  `PLAYER1_IDN` BIGINT NULL ,
  `PLAYER2_IDN` BIGINT NULL ,
  `PLAYER3_IDN` BIGINT NULL ,
  `PLAYER4_IDN` BIGINT NULL ,
  `PLAYER5_IDN` BIGINT NULL ,
  `PLAYER6_IDN` BIGINT NULL ,
  `PLAYER7_IDN` BIGINT NULL ,
  `PLAYER8_IDN` BIGINT NULL ,
  PRIMARY KEY (`GAME_INSTANCE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAME_ROUND_RESULTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAME_ROUND_RESULTS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAME_ROUND_RESULTS` (
  `GAME_ROUND_ID` VARCHAR(20) NOT NULL ,
  `GAME_INSTANCE_ID` VARCHAR(65) NOT NULL ,
  `USER_ID` BIGINT NOT NULL ,
  `POINTS` INT NULL ,
  `CASH` DOUBLE NULL ,
  `HAS_WON` TINYINT(1) NULL ,
  PRIMARY KEY (`GAME_ROUND_ID`, `GAME_INSTANCE_ID`, `USER_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAME_ROUND`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAME_ROUND` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAME_ROUND` (
  `GAME_ROUND_ID` VARCHAR(20) NOT NULL ,
  `GAME_INSTANCE_ID` VARCHAR(65) NOT NULL ,
  `STATUS` VARCHAR(35) NULL ,
  `CREATED_DATE` DATETIME NULL ,
  `COMPLETED_DATE` DATETIME NULL ,
  PRIMARY KEY (`GAME_ROUND_ID`, `GAME_INSTANCE_ID`) ,
  INDEX `FK_GAME_RESULTS_idx` (`GAME_ROUND_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAME`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAME` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAME` (
  `GAME_INSTANCE_ID` VARCHAR(65) NOT NULL ,
  `GAME_LOBBY` VARCHAR(45) NOT NULL ,
  `GAME_TYPE` VARCHAR(45) NOT NULL ,
  `HOSTED_BY` VARCHAR(45) NOT NULL ,
  `CREATED_DATE` DATETIME NOT NULL ,
  `IS_ACTIVE` TINYINT(1) NULL ,
  `CANCELLED_DATE` DATETIME NULL ,
  `COMPLETED_DATE` DATETIME NULL ,
  `POINTS_BASED` TINYINT(1) NULL ,
  `PERCARD_BASED` TINYINT(1) NULL ,
  PRIMARY KEY (`GAME_INSTANCE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`USER_PROFILE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_PROFILE` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_PROFILE` (
  `USERID` BIGINT NOT NULL ,
  `FIRSTNAME` VARCHAR(50) NULL ,
  `LASTNAME` VARCHAR(50) NULL ,
  `COUNTRY` VARCHAR(45) NULL ,
  PRIMARY KEY (`USERID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`USER_NOTIFICATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_NOTIFICATIONS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_NOTIFICATIONS` (
  `USERID` BIGINT NOT NULL ,
  `NOTIFICATION_IDN` BIGINT NOT NULL ,
  `NOTIFICATION_TYPE` VARCHAR(45) NULL ,
  `NOTIFIER_SRC_KEY` BIGINT NULL ,
  `NOTIFICATION_DESC` VARCHAR(45) NULL ,
  `NOTIFIED_BY` VARCHAR(45) NULL ,
  `NOTIFICATION_STATUS` VARCHAR(45) NULL ,
  `CREATED_DATE` DATETIME NULL ,
  PRIMARY KEY (`USERID`, `NOTIFICATION_IDN`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`FRIEND_REQUEST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`FRIEND_REQUEST` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`FRIEND_REQUEST` (
  `REQUEST_IDN` BIGINT NOT NULL AUTO_INCREMENT ,
  `REQUESTOR_IDN` BIGINT NULL ,
  `RECEIVER_IDN` BIGINT NULL ,
  `REQUESTED_DATE` DATETIME NULL ,
  `STATUS` VARCHAR(45) NULL ,
  PRIMARY KEY (`REQUEST_IDN`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAMEJOIN_REQUEST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAMEJOIN_REQUEST` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAMEJOIN_REQUEST` (
  `REQUEST_ID` BIGINT NOT NULL ,
  `REQUESTOR_IDN` BIGINT NULL ,
  `GAME_INSTANCE_ID` VARCHAR(65) NULL ,
  PRIMARY KEY (`REQUEST_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`USER_FRIENDS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`USER_FRIENDS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`USER_FRIENDS` (
  `USER_ID` BIGINT NOT NULL ,
  `LINK_ID` BIGINT NOT NULL AUTO_INCREMENT ,
  `FRIENDS_IDN` BIGINT NULL ,
  `ESTABLISHED_DATE` DATETIME NOT NULL ,
  `EXPIRATION_DATE` DATETIME NOT NULL ,
  PRIMARY KEY (`LINK_ID`, `USER_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rummy`.`GAME_STATS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rummy`.`GAME_STATS` ;

CREATE  TABLE IF NOT EXISTS `Rummy`.`GAME_STATS` (
  `GAME_INSTANCE_ID` VARCHAR(65) NOT NULL ,
  `WON_BY_USERID` BIGINT NOT NULL ,
  PRIMARY KEY (`GAME_INSTANCE_ID`, `WON_BY_USERID`) )
ENGINE = InnoDB;

USE `Rummy` ;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO rummy;
 DROP USER rummy;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'rummy' IDENTIFIED BY 'P3ecost1';

GRANT ALL ON `Rummy`.* TO 'rummy';
GRANT SELECT ON TABLE `Rummy`.* TO 'rummy';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `Rummy`.* TO 'rummy';
GRANT SELECT, INSERT, TRIGGER ON TABLE `Rummy`.* TO 'rummy';
-- GRANT EXECUTE ON ROUTINE `Rummy`.* TO 'rummy';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
