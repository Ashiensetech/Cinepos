ALTER TABLE `circuit` CHANGE `created_by` `created_by` INT(11) NULL DEFAULT '0';
ALTER TABLE `circuit` CHANGE `updated_by` `updated_by` INT(11) NULL DEFAULT '0';

ALTER TABLE `circuit` CHANGE `refund_deduction_percentange` `refund_deduction_percentage` VARCHAR(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `circuit` CHANGE `refund_deduction_percentage` `refund_deduction_percentage` DECIMAL(8,2) NOT NULL;
