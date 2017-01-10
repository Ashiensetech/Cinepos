ALTER TABLE `terminal` ADD `terminal_code` INT NOT NULL AFTER `ip_address`;
ALTER TABLE `terminal` CHANGE `terminal_code` `terminal_code` VARCHAR(11) NOT NULL;
ALTER TABLE `terminal` CHANGE `terminal_code` `terminal_code` VARCHAR(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
