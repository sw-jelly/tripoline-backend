-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tripoline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tripoline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tripoline` DEFAULT CHARACTER SET utf8 ;
USE `tripoline` ;

-- -----------------------------------------------------
-- Table `tripoline`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `fk_sido_gugun_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `fk_sido_gugun`
    FOREIGN KEY (`sido_code`)
    REFERENCES `tripoline`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`members` (
  `member_id` VARCHAR(16) NOT NULL,
  `member_password` VARCHAR(100) NOT NULL,
  `member_email` VARCHAR(70) NULL,
  `member_name` VARCHAR(45) NULL,
  `sido_code` INT NULL,
  `gugun_code` INT NULL,
  `member_photo` VARCHAR(100) NULL,
  `member_birthdate` DATE NULL,
  `member_gender` CHAR(1) NULL,
  `member_phone` VARCHAR(45) NULL,
  `member_role` INT NULL,
  PRIMARY KEY (`member_id`),
  INDEX `fk_sido_members_idx` (`sido_code` ASC) VISIBLE,
  INDEX `fk_gugun_members_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `fk_sido_members`
    FOREIGN KEY (`sido_code`)
    REFERENCES `tripoline`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gugun_members`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `tripoline`.`gugun` (`gugun_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`categories` (
  `category_id` INT NOT NULL,
  `category_name` VARCHAR(40) NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`articles` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `article_title` VARCHAR(100) NULL,
  `article_content` VARCHAR(5000) NULL,
  `member_id` VARCHAR(16) NULL,
  `member_name` VARCHAR(45) NULL,
  `like` INT NULL DEFAULT 0,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `update_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `category_id` INT NULL,
  `image` VARCHAR(200) NULL,
  PRIMARY KEY (`article_id`),
  INDEX `fk_members_articles_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_categories_articles_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_members_articles`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripoline`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categories_articles`
    FOREIGN KEY (`category_id`)
    REFERENCES `tripoline`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL,
  `title` VARCHAR(100) NULL,
  `addr1` VARCHAR(100) NULL,
  `addr2` VARCHAR(50) NULL,
  `zipcode` VARCHAR(50) NULL,
  `tel` VARCHAR(50) NULL,
  `first_image` VARCHAR(200) NULL,
  `first_image2` VARCHAR(200) NULL,
  `sido_code` INT NULL,
  `gugun_code` INT NULL,
  `latitude` DECIMAL(20,17) NULL,
  `longitude` DECIMAL(20,17) NULL,
  `overview` VARCHAR(10000) NULL,
  PRIMARY KEY (`content_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`favorites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`favorites` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(16) NULL,
  `content_id` INT NULL,
  PRIMARY KEY (`favorite_id`),
  INDEX `fk_members_favorites_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_attraction_info_favorites_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `fk_members_favorites`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripoline`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attraction_info_favorites`
    FOREIGN KEY (`content_id`)
    REFERENCES `tripoline`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripoline`.`plans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`plans` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(16) NULL,
  `plan_title` VARCHAR(100) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `sido_code` INT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `fk_members_plans_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_sido_plans_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `fk_members_plans`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripoline`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sido_plans`
    FOREIGN KEY (`sido_code`)
    REFERENCES `tripoline`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `tripoline`.`plan_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripoline`.`plan_detail` (
  `plan_detail_id` INT NOT NULL AUTO_INCREMENT,
  `plan_id` INT NULL,
  `content_id` INT NULL,
  `content_type_id` INT NULL,
  `visit_date` DATE NULL,
  `visit_time` DATETIME NULL,
  `visit_order` INT NULL,
  PRIMARY KEY (`plan_detail_id`),
  INDEX `fk_plans_plan_detail_idx` (`plan_id` ASC) VISIBLE,
  INDEX `fk_attraction_info_plan_detail_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `fk_plans_plan_detail`
    FOREIGN KEY (`plan_id`)
    REFERENCES `tripoline`.`plans` (`plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attraction_info_plan_detail`
    FOREIGN KEY (`content_id`)
    REFERENCES `tripoline`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
