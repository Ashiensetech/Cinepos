ALTER TABLE  `screen_seat` ADD  `is_seat_plan_complete` TINYINT( 1 ) NOT NULL AFTER  `name` ;
ALTER TABLE  `seat_type` ADD  `is_default` TINYINT( 1 ) NOT NULL AFTER  `name` ;