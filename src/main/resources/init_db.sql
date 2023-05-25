CREATE SCHEMA IF NOT EXISTS `cook_book` DEFAULT CHARACTER SET utf8;
USE `cook_book`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `cookbooks`;
CREATE TABLE `cookbooks`  (
                            `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `recipes`;

CREATE TABLE `manufacturers`  (
                                  `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `description` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `publication_date` DATETIME(6) NOT NULL,
                                  `cookbook_id` BIGINT(0) NOT NULL,
                                  `parent_recipe_id` BIGINT(0) NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
