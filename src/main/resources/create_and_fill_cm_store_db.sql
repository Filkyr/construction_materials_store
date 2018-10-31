-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cm_store
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cm_store` ;

-- -----------------------------------------------------
-- Schema cm_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cm_store` DEFAULT CHARACTER SET utf8 ;
USE `cm_store` ;

-- -----------------------------------------------------
-- Table `cm_store`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`category` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cm_store`.`producer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`producer` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`producer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `brand_name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `logo` VARCHAR(1024) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cm_store`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`product` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `category_id` INT NOT NULL,
  `producer_id` INT NOT NULL,
  `image` VARCHAR(1024) NULL,
  `description` TEXT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `id_category_idx` (`category_id` ASC) VISIBLE,
  INDEX `id_producer_idx` (`producer_id` ASC) VISIBLE,
  CONSTRAINT `product_category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `cm_store`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_producer_id_fk`
    FOREIGN KEY (`producer_id`)
    REFERENCES `cm_store`.`producer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cm_store`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`customer` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_num` VARCHAR(45) NULL,
  `adress` VARCHAR(135) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cm_store`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`order` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_customer_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `order_customert_id_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `cm_store`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cm_store`.`order_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cm_store`.`order_product` ;

CREATE TABLE IF NOT EXISTS `cm_store`.`order_product` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_order_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `cm_store`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `cm_store`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cm_store`.`category`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`category` (`id`, `title`, `description`) VALUES (1, 'Drills and hammers', 'Some nice drills that can drill you wall');
INSERT INTO `cm_store`.`category` (`id`, `title`, `description`) VALUES (2, 'Light bulbs', 'Incandescent, LED, halogen lamps and etc.');
INSERT INTO `cm_store`.`category` (`id`, `title`, `description`) VALUES (3, 'Doors', 'From wood, metal and plastic. All colours and sizes');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cm_store`.`producer`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`producer` (`id`, `brand_name`, `description`, `logo`) VALUES (1, 'Bosch', 'Bosch, is a world leading multinational engineering and electronics company headquartered in Gerlingen, near Stuttgart, Germany. The company was founded by Robert Bosch in Stuttgart in 1886. Bosch is 92% owned by Robert Bosch Stiftung.', 'https://www.bosch-professional.com/pro-challenge/typo3conf/ext/zwbisdrei_template/Resources/Public/Images/logo-de.jpg');
INSERT INTO `cm_store`.`producer` (`id`, `brand_name`, `description`, `logo`) VALUES (2, 'Philips', 'Dutch multinational technology company headquartered in Amsterdam currently focused in the area of healthcare and lighting. It was founded in Eindhoven in 1891 by Gerard Philips and his father Frederik, with their first products being light bulbs. It was once one of the largest electronic conglomerates in the world and currently employs around 74,000 people across 100 countries. The company gained its royal honorary title in 1998 and dropped the \"Electronics\" in its name in 2013.', 'https://www.philips.com/consumerfiles/newscenter/main/standard/resources/corporate/press/2013/Brand/Philips-Shield.jpg');
INSERT INTO `cm_store`.`producer` (`id`, `brand_name`, `description`, `logo`) VALUES (3, 'MegaDoors', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eleifend dolor auctor, feugiat libero vitae, tincidunt mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam ac tortor vel nisl cursus vehicula at ut diam. Etiam accumsan lorem eu enim efficitur ornare. Etiam ornare erat ac erat ornare varius. In quis purus sit amet ipsum malesuada tempor. Sed laoreet rutrum aliquam. Fusce tincidunt nisl sit amet dui euismod molestie.', 'https://www.glenviewdoors.com/PRODUCT-DETAILS-Custom-Entry-Doors-GD/011_SL_DD_CST_Mahogany-Dark/big.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cm_store`.`product`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`product` (`id`, `title`, `category_id`, `producer_id`, `image`, `description`) VALUES (1, 'BSCH-3255', 1, 1, 'https://img.purch.com/r/520x520/aHR0cHM6Ly93d3cudG9wdGVucmV2aWV3cy5jb20vaS9wZHAvNjMyN2M2NmIzOWQ2MTFiMTk1YzI5MDQxOTU3YmM1MGEuanBn', 'Good and expensive drill');
INSERT INTO `cm_store`.`product` (`id`, `title`, `category_id`, `producer_id`, `image`, `description`) VALUES (2, 'PHILL-5678', 2, 2, 'http://www.assets.lighting.philips.com/is/image/PhilipsLighting/35ac2e5642124ea3ae98a4a3007b2657?$jpgsmall$&wid=305&hei=305', 'Some nice lamp');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cm_store`.`customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`customer` (`id`, `first_name`, `last_name`, `phone_num`, `adress`) VALUES (1, 'Kiryl', 'Vasiliev', '+375774567389', NULL);
INSERT INTO `cm_store`.`customer` (`id`, `first_name`, `last_name`, `phone_num`, `adress`) VALUES (2, 'Anton', 'Aezakmi', '+378956879987', NULL);
INSERT INTO `cm_store`.`customer` (`id`, `first_name`, `last_name`, `phone_num`, `adress`) VALUES (3, 'Kostya', 'Soname', '+378985456478', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cm_store`.`order`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`order` (`id`, `customer_id`, `date`) VALUES (1, 1, '2018-10-10 20:10:59');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cm_store`.`order_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `cm_store`;
INSERT INTO `cm_store`.`order_product` (`order_id`, `product_id`) VALUES (1, 1);
INSERT INTO `cm_store`.`order_product` (`order_id`, `product_id`) VALUES (1, 2);

COMMIT;

