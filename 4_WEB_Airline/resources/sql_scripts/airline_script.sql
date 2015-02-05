-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema airline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema airline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airline` DEFAULT CHARACTER SET utf8 ;
USE `airline` ;

-- -----------------------------------------------------
-- Table `airline`.`aircraft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`aircraft` (
  `id_aircraft_reg` VARCHAR(10) NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  `model_type` VARCHAR(45) NOT NULL,
  `num_seats` INT(11) NOT NULL,
  `num_pilots` INT(11) NOT NULL,
  `num_navigators` INT(11) NOT NULL,
  `num_radiomen` INT(11) NOT NULL,
  `num_engineers` INT(11) NOT NULL,
  `num_attendants` INT(11) NOT NULL,
  PRIMARY KEY (`id_aircraft_reg`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airline`.`airport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`airport` (
  `id_airport` VARCHAR(4) NOT NULL,
  `title` VARCHAR(60) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_airport`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airline`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`employee` (
  `id_employee` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airline`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`flight` (
  `id_flight_num` VARCHAR(8) NOT NULL,
  `dep_time` TIME NOT NULL,
  `arr_time` TIME NOT NULL,
  `dep_airport` VARCHAR(4) NOT NULL,
  `arr_airport` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id_flight_num`),
  INDEX `fk_flight_airport_idx` (`dep_airport` ASC),
  INDEX `fk_flight_airport1_idx` (`arr_airport` ASC),
  CONSTRAINT `fk_flight_airport`
    FOREIGN KEY (`dep_airport`)
    REFERENCES `airline`.`airport` (`id_airport`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flight_airport1`
    FOREIGN KEY (`arr_airport`)
    REFERENCES `airline`.`airport` (`id_airport`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airline`.`shuttle_flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`shuttle_flight` (
  `id_flight_date` DATE NOT NULL,
  `aircraft_reg` VARCHAR(10) NOT NULL,
  `id_flight_num` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id_flight_date`, `id_flight_num`),
  INDEX `fk_shuttle_flight_aircraft1_idx` (`aircraft_reg` ASC),
  INDEX `fk_shuttle_flight_flight1_idx` (`id_flight_num` ASC),
  CONSTRAINT `fk_shuttle_flight_aircraft1`
    FOREIGN KEY (`aircraft_reg`)
    REFERENCES `airline`.`aircraft` (`id_aircraft_reg`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_shuttle_flight_flight1`
    FOREIGN KEY (`id_flight_num`)
    REFERENCES `airline`.`flight` (`id_flight_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airline`.`cabin_crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airline`.`cabin_crew` (
  `id_employee` INT(11) NOT NULL,
  `id_flight_date` DATE NOT NULL,
  `id_flight_num` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id_employee`, `id_flight_date`, `id_flight_num`),
  INDEX `fk_employee_has_shuttle_flight_employee1_idx` (`id_employee` ASC),
  INDEX `fk_cabin_crew_shuttle_flight1_idx` (`id_flight_date` ASC, `id_flight_num` ASC),
  CONSTRAINT `fk_cabin_crew_employee1`
    FOREIGN KEY (`id_employee`)
    REFERENCES `airline`.`employee` (`id_employee`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_cabin_crew_shuttle_flight1`
    FOREIGN KEY (`id_flight_date` , `id_flight_num`)
    REFERENCES `airline`.`shuttle_flight` (`id_flight_date` , `id_flight_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
