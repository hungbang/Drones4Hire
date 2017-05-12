-- MySQL Script generated by MySQL Workbench
-- Fri May 12 15:14:38 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Drones4Hire
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Drones4Hire` ;

-- -----------------------------------------------------
-- Schema Drones4Hire
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Drones4Hire` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Drones4Hire` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `username` (16) NOT NULL,
  `email` (255) NULL,
  `password` (32) NOT NULL,
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `timestamps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `timestamps` ;

CREATE TABLE IF NOT EXISTS `timestamps` (
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time`  NULL);


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `category_id`  NOT NULL,
  `name` (255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `COUNTRIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COUNTRIES` ;

CREATE TABLE IF NOT EXISTS `COUNTRIES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `STATES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `STATES` ;

CREATE TABLE IF NOT EXISTS `STATES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LOCATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LOCATIONS` ;

CREATE TABLE IF NOT EXISTS `LOCATIONS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ADDRESS` VARCHAR(255) NULL,
  `COORDINATES` POINT NULL,
  `COUNTRY_ID` INT UNSIGNED NULL,
  `CITY` VARCHAR(50) NULL,
  `STATE_ID` INT UNSIGNED NULL,
  `POSTCODE` INT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_LOCATIONS_COUNTRIES1_idx` (`COUNTRY_ID` ASC),
  INDEX `fk_LOCATIONS_STATES1_idx` (`STATE_ID` ASC),
  CONSTRAINT `fk_LOCATIONS_COUNTRIES1`
    FOREIGN KEY (`COUNTRY_ID`)
    REFERENCES `COUNTRIES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LOCATIONS_STATES1`
    FOREIGN KEY (`STATE_ID`)
    REFERENCES `STATES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USERS` ;

CREATE TABLE IF NOT EXISTS `USERS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(50) NOT NULL,
  `EMAIL` VARCHAR(100) NOT NULL,
  `PASSWORD` VARCHAR(50) NOT NULL,
  `FIRST_NAME` VARCHAR(50) NULL,
  `LAST_NAME` VARCHAR(50) NULL,
  `PHOTO_URL` TINYTEXT NULL,
  `INTRODUCTION` TINYTEXT NULL,
  `SUMMARY` TINYTEXT NULL,
  `LOCATION_ID` INT UNSIGNED NULL,
  `ENABLED` TINYINT(1) NOT NULL DEFAULT 1,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC),
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC),
  INDEX `fk_USERS_LOCATIONS1_idx` (`LOCATION_ID` ASC),
  CONSTRAINT `fk_USERS_LOCATIONS1`
    FOREIGN KEY (`LOCATION_ID`)
    REFERENCES `LOCATIONS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GROUPS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GROUPS` ;

CREATE TABLE IF NOT EXISTS `GROUPS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL,
  `ROLE` VARCHAR(50) NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_1` ;

CREATE TABLE IF NOT EXISTS `user_1` (
  `username` (16) NOT NULL,
  `email` (255) NULL,
  `password` (32) NOT NULL,
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `user_2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_2` ;

CREATE TABLE IF NOT EXISTS `user_2` (
  `username` (16) NOT NULL,
  `email` (255) NULL,
  `password` (32) NOT NULL,
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `USER_GROUPS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_GROUPS` ;

CREATE TABLE IF NOT EXISTS `USER_GROUPS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `GROUP_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_USER_GROUPS_USERS1_idx` (`USER_ID` ASC),
  INDEX `fk_USER_GROUPS_GROUPS1_idx` (`GROUP_ID` ASC),
  CONSTRAINT `fk_USER_GROUPS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_GROUPS_GROUPS1`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `GROUPS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_3`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_3` ;

CREATE TABLE IF NOT EXISTS `user_3` (
  `username` (16) NOT NULL,
  `email` (255) NULL,
  `password` (32) NOT NULL,
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `SERVICE_CATEGORIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SERVICE_CATEGORIES` ;

CREATE TABLE IF NOT EXISTS `SERVICE_CATEGORIES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `ORDER` INT NOT NULL DEFAULT 0,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SERVICES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SERVICES` ;

CREATE TABLE IF NOT EXISTS `SERVICES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `SERVICE_CATEGORY_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC),
  INDEX `fk_SERVICES_SERVICE_CATEGORIES1_idx` (`SERVICE_CATEGORY_ID` ASC),
  CONSTRAINT `fk_SERVICES_SERVICE_CATEGORIES1`
    FOREIGN KEY (`SERVICE_CATEGORY_ID`)
    REFERENCES `SERVICE_CATEGORIES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `USER_SERVICES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_SERVICES` ;

CREATE TABLE IF NOT EXISTS `USER_SERVICES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `SERVICE_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_USER_SERVICES_SERVICES1_idx` (`SERVICE_ID` ASC),
  INDEX `fk_USER_SERVICES_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_USER_SERVICES_SERVICES1`
    FOREIGN KEY (`SERVICE_ID`)
    REFERENCES `SERVICES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_SERVICES_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROFILES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PROFILES` ;

CREATE TABLE IF NOT EXISTS `PROFILES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `TAGLINE` VARCHAR(255) NULL,
  `BIO` TEXT NULL,
  `WEB_URL` TINYTEXT NULL,
  `COMPANY_LOGO_URL` TINYTEXT NULL,
  `COVER_PHOTO_URL` TINYTEXT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PROFILES_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_PROFILES_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `COMPANIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMPANIES` ;

CREATE TABLE IF NOT EXISTS `COMPANIES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERS_ID` INT UNSIGNED NOT NULL,
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `WEB_URL` TINYTEXT NULL,
  `CONTACT_NAME` VARCHAR(100) NULL,
  `CONTACT_EMAIL` VARCHAR(100) NULL,
  `COUNTRY_ID` INT UNSIGNED NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_COMPANIES_COUNTRIES1_idx` (`COUNTRY_ID` ASC),
  INDEX `fk_COMPANIES_USERS1_idx` (`USERS_ID` ASC),
  CONSTRAINT `fk_COMPANIES_COUNTRIES1`
    FOREIGN KEY (`COUNTRY_ID`)
    REFERENCES `COUNTRIES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMPANIES_USERS1`
    FOREIGN KEY (`USERS_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PILOT_LICENSES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PILOT_LICENSES` ;

CREATE TABLE IF NOT EXISTS `PILOT_LICENSES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `LICENSE_URL` TINYTEXT NULL,
  `INSURANCE_URL` TINYTEXT NULL,
  `VERIFIED` TINYINT(1) NOT NULL DEFAULT 0,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PILOT_LICENSES_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_PILOT_LICENSES_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PORTFOLIO_ITEMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PORTFOLIO_ITEMS` ;

CREATE TABLE IF NOT EXISTS `PORTFOLIO_ITEMS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `NAME` VARCHAR(50) NOT NULL,
  `TITLE` VARCHAR(255) NOT NULL,
  `SUMMARY` TINYTEXT NULL,
  `TYPE` VARCHAR(20) NOT NULL,
  `ITEM_URL` TINYTEXT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PORTFOLIO_ITEMS_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_PORTFOLIO_ITEMS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PORTFOLIO_CATEGORIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PORTFOLIO_CATEGORIES` ;

CREATE TABLE IF NOT EXISTS `PORTFOLIO_CATEGORIES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PORTFOLIO_ITEM_ID` INT UNSIGNED NOT NULL,
  `SERVICE_CATEGORY_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PORTFOLIO_CATEGORIES_PORTFOLIO_ITEMS1_idx` (`PORTFOLIO_ITEM_ID` ASC),
  INDEX `fk_PORTFOLIO_CATEGORIES_SERVICE_CATEGORIES1_idx` (`SERVICE_CATEGORY_ID` ASC),
  CONSTRAINT `fk_PORTFOLIO_CATEGORIES_PORTFOLIO_ITEMS1`
    FOREIGN KEY (`PORTFOLIO_ITEM_ID`)
    REFERENCES `PORTFOLIO_ITEMS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PORTFOLIO_CATEGORIES_SERVICE_CATEGORIES1`
    FOREIGN KEY (`SERVICE_CATEGORY_ID`)
    REFERENCES `SERVICE_CATEGORIES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PORTFOLIO_CATEGORIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PORTFOLIO_CATEGORIES` ;

CREATE TABLE IF NOT EXISTS `PORTFOLIO_CATEGORIES` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PORTFOLIO_ITEM_ID` INT UNSIGNED NOT NULL,
  `SERVICE_CATEGORY_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PORTFOLIO_CATEGORIES_PORTFOLIO_ITEMS1_idx` (`PORTFOLIO_ITEM_ID` ASC),
  INDEX `fk_PORTFOLIO_CATEGORIES_SERVICE_CATEGORIES1_idx` (`SERVICE_CATEGORY_ID` ASC),
  CONSTRAINT `fk_PORTFOLIO_CATEGORIES_PORTFOLIO_ITEMS1`
    FOREIGN KEY (`PORTFOLIO_ITEM_ID`)
    REFERENCES `PORTFOLIO_ITEMS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PORTFOLIO_CATEGORIES_SERVICE_CATEGORIES1`
    FOREIGN KEY (`SERVICE_CATEGORY_ID`)
    REFERENCES `SERVICE_CATEGORIES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PILOT_LOCATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PILOT_LOCATIONS` ;

CREATE TABLE IF NOT EXISTS `PILOT_LOCATIONS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `OFFICE` VARCHAR(255) NOT NULL,
  `PHONE` VARCHAR(50) NOT NULL,
  `ALTERNATIVE_PHONE` VARCHAR(50) NULL,
  `LOCATION_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PILOT_LOCATIONS_USERS1_idx` (`USER_ID` ASC),
  INDEX `fk_PILOT_LOCATIONS_LOCATIONS1_idx` (`LOCATION_ID` ASC),
  CONSTRAINT `fk_PILOT_LOCATIONS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PILOT_LOCATIONS_LOCATIONS1`
    FOREIGN KEY (`LOCATION_ID`)
    REFERENCES `LOCATIONS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BUDGETS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BUDGETS` ;

CREATE TABLE IF NOT EXISTS `BUDGETS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) NOT NULL,
  `MIN` DECIMAL(20,2) NOT NULL,
  `MAX` DECIMAL(20,2) NOT NULL,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `ORDER` INT NOT NULL DEFAULT 0,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `TITLE_UNIQUE` (`TITLE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DURATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DURATIONS` ;

CREATE TABLE IF NOT EXISTS `DURATIONS` (
  `ID` INT NOT NULL,
  `TITLE` VARCHAR(50) NOT NULL,
  `MIN` DECIMAL NOT NULL,
  `MAX` DECIMAL NOT NULL,
  `ORDER` INT NOT NULL DEFAULT 0,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `TITLE_UNIQUE` (`TITLE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROJECTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PROJECTS` ;

CREATE TABLE IF NOT EXISTS `PROJECTS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(255) NOT NULL,
  `SUMMARY` TINYTEXT NULL,
  `CLIENT_ID` INT UNSIGNED NOT NULL,
  `PILOT_ID` INT UNSIGNED NULL,
  `SERVICE_ID` INT UNSIGNED NOT NULL,
  `DURATION_ID` INT NOT NULL,
  `LOCATION_ID` INT UNSIGNED NOT NULL,
  `BUDGET_ID` INT UNSIGNED NOT NULL,
  `IMAGE_URL` TINYTEXT NULL,
  `POST_PRODUCTION_REQUIRED` TINYINT(1) NOT NULL DEFAULT 0,
  `STATUS` VARCHAR(50) NOT NULL,
  `START_DATE` TIMESTAMP NOT NULL,
  `FINISH_DATE` TIMESTAMP NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_PROJECTS_USERS1_idx` (`CLIENT_ID` ASC),
  INDEX `fk_PROJECTS_USERS2_idx` (`PILOT_ID` ASC),
  INDEX `fk_PROJECTS_BUDGETS1_idx` (`BUDGET_ID` ASC),
  INDEX `fk_PROJECTS_SERVICES1_idx` (`SERVICE_ID` ASC),
  INDEX `fk_PROJECTS_DURATIONS1_idx` (`DURATION_ID` ASC),
  INDEX `fk_PROJECTS_LOCATIONS1_idx` (`LOCATION_ID` ASC),
  CONSTRAINT `fk_PROJECTS_USERS1`
    FOREIGN KEY (`CLIENT_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_USERS2`
    FOREIGN KEY (`PILOT_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_BUDGETS1`
    FOREIGN KEY (`BUDGET_ID`)
    REFERENCES `BUDGETS` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_SERVICES1`
    FOREIGN KEY (`SERVICE_ID`)
    REFERENCES `SERVICES` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_DURATIONS1`
    FOREIGN KEY (`DURATION_ID`)
    REFERENCES `DURATIONS` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_LOCATIONS1`
    FOREIGN KEY (`LOCATION_ID`)
    REFERENCES `LOCATIONS` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BIDS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BIDS` ;

CREATE TABLE IF NOT EXISTS `BIDS` (
  `ID` INT NOT NULL,
  `AMOUNT` DECIMAL(20,2) NOT NULL,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `COMMENT` TINYTEXT NULL,
  `PROJECT_ID` INT UNSIGNED NOT NULL,
  `USER_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_BIDS_PROJECTS1_idx` (`PROJECT_ID` ASC),
  INDEX `fk_BIDS_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_BIDS_PROJECTS1`
    FOREIGN KEY (`PROJECT_ID`)
    REFERENCES `PROJECTS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BIDS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FEEDBACKS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FEEDBACKS` ;

CREATE TABLE IF NOT EXISTS `FEEDBACKS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `FROM_USER_ID` INT UNSIGNED NOT NULL,
  `TO_USER_ID` INT UNSIGNED NOT NULL,
  `PROJECT_ID` INT UNSIGNED NOT NULL,
  `MARK` DECIMAL NOT NULL DEFAULT 0,
  `COMMENT` TINYTEXT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_FEEDBACKS_USERS1_idx` (`FROM_USER_ID` ASC),
  INDEX `fk_FEEDBACKS_USERS2_idx` (`TO_USER_ID` ASC),
  INDEX `fk_FEEDBACKS_PROJECTS1_idx` (`PROJECT_ID` ASC),
  CONSTRAINT `fk_FEEDBACKS_USERS1`
    FOREIGN KEY (`FROM_USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FEEDBACKS_USERS2`
    FOREIGN KEY (`TO_USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FEEDBACKS_PROJECTS1`
    FOREIGN KEY (`PROJECT_ID`)
    REFERENCES `PROJECTS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WALLETS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WALLETS` ;

CREATE TABLE IF NOT EXISTS `WALLETS` (
  `ID` INT NOT NULL,
  `USER_ID` INT UNSIGNED NOT NULL,
  `BALANCE` DECIMAL(20,2) NOT NULL DEFAULT 0,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `PAYMENT_TOKEN` VARCHAR(255) NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_WALLETS_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_WALLETS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TRANSACTIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TRANSACTIONS` ;

CREATE TABLE IF NOT EXISTS `TRANSACTIONS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `WALLET_ID` INT UNSIGNED NOT NULL,
  `AMOUNT` DECIMAL(20,2) NOT NULL,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `TYPE` VARCHAR(20) NOT NULL,
  `PURPOSE` TINYTEXT NULL,
  `PROJECT_ID` INT UNSIGNED NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_TRANSACTIONS_WALLETS1_idx` (`WALLET_ID` ASC),
  INDEX `fk_TRANSACTIONS_PROJECTS1_idx` (`PROJECT_ID` ASC),
  CONSTRAINT `fk_TRANSACTIONS_WALLETS1`
    FOREIGN KEY (`WALLET_ID`)
    REFERENCES `WALLETS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TRANSACTIONS_PROJECTS1`
    FOREIGN KEY (`PROJECT_ID`)
    REFERENCES `PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `WITHDRAW_REQUESTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `WITHDRAW_REQUESTS` ;

CREATE TABLE IF NOT EXISTS `WITHDRAW_REQUESTS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TRANSACTION_ID` INT UNSIGNED NOT NULL,
  `USER_ID` INT UNSIGNED NOT NULL,
  `AMOUNT` DECIMAL(20,2) NOT NULL,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `COMMENT` TINYTEXT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_WITHDRAW_REQUESTS_USERS1_idx` (`USER_ID` ASC),
  INDEX `fk_WITHDRAW_REQUESTS_TRANSACTIONS1_idx` (`TRANSACTION_ID` ASC),
  CONSTRAINT `fk_WITHDRAW_REQUESTS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WITHDRAW_REQUESTS_TRANSACTIONS1`
    FOREIGN KEY (`TRANSACTION_ID`)
    REFERENCES `TRANSACTIONS` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `COMMENTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMMENTS` ;

CREATE TABLE IF NOT EXISTS `COMMENTS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMMENT` TINYTEXT NOT NULL,
  `USER_ID` INT UNSIGNED NOT NULL,
  `PROJECT_ID` INT UNSIGNED NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_COMMENTS_USERS1_idx` (`USER_ID` ASC),
  INDEX `fk_COMMENTS_PROJECTS1_idx` (`PROJECT_ID` ASC),
  CONSTRAINT `fk_COMMENTS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMMENTS_PROJECTS1`
    FOREIGN KEY (`PROJECT_ID`)
    REFERENCES `PROJECTS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NOTIFICATION_SETTINGS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NOTIFICATION_SETTINGS` ;

CREATE TABLE IF NOT EXISTS `NOTIFICATION_SETTINGS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERS_ID` INT UNSIGNED NOT NULL,
  `PLAIN_EMAIL` TINYINT(1) NOT NULL DEFAULT 0,
  `BID_PLACED` TINYINT(1) NOT NULL DEFAULT 0,
  `PAYMENT_RECEIVED` TINYINT(1) NOT NULL DEFAULT 0,
  `PROJECT_UPDATE` TINYINT(1) NOT NULL DEFAULT 0,
  `STAFF` TINYINT(1) NOT NULL DEFAULT 0,
  `DRONES_NEWS` TINYINT(1) NOT NULL DEFAULT 0,
  `PROJECT_AWARD` TINYINT(1) NOT NULL DEFAULT 0,
  `MARKETING` TINYINT(1) NOT NULL DEFAULT 0,
  `DEALS` TINYINT(1) NOT NULL DEFAULT 0,
  `MONTHLY_NEWS` TINYINT(1) NOT NULL DEFAULT 0,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  INDEX `fk_NOTIFICATION_SETTINGS_USERS1_idx` (`USERS_ID` ASC),
  CONSTRAINT `fk_NOTIFICATION_SETTINGS_USERS1`
    FOREIGN KEY (`USERS_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PAID_OPTIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PAID_OPTIONS` ;

CREATE TABLE IF NOT EXISTS `PAID_OPTIONS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NOT NULL,
  `PRICE` DECIMAL(2,20) NOT NULL DEFAULT 0,
  `CURRENCY` VARCHAR(10) NOT NULL DEFAULT 'USD',
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROJECTS_PAID_OPTIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PROJECTS_PAID_OPTIONS` ;

CREATE TABLE IF NOT EXISTS `PROJECTS_PAID_OPTIONS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` INT UNSIGNED NOT NULL,
  `PAID_OPTION_ID` INT NOT NULL,
  `MODIFIED_AT` TIMESTAMP NOT NULL DEFAULT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT 'TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP',
  PRIMARY KEY (`ID`, `PROJECT_ID`, `PAID_OPTION_ID`),
  INDEX `fk_PROJECTS_has_PAID_OPTIONS_PAID_OPTIONS1_idx` (`PAID_OPTION_ID` ASC),
  INDEX `fk_PROJECTS_has_PAID_OPTIONS_PROJECTS1_idx` (`PROJECT_ID` ASC),
  CONSTRAINT `fk_PROJECTS_has_PAID_OPTIONS_PROJECTS1`
    FOREIGN KEY (`PROJECT_ID`)
    REFERENCES `PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJECTS_has_PAID_OPTIONS_PAID_OPTIONS1`
    FOREIGN KEY (`PAID_OPTION_ID`)
    REFERENCES `PAID_OPTIONS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
