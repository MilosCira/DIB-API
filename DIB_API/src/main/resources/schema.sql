DROP TABLE IF EXISTS beer;

CREATE TABLE beer (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  description VARCHAR(555) NULL,
  temperature DOUBLE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC));