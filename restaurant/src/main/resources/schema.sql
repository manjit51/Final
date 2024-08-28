DROP TABLE IF EXISTS restaurant_patron;
DROP TABLE IF EXISTS employee; 
DROP TABLE IF EXISTS patron;
DROP TABLE IF EXISTS restaurant;

CREATE TABLE restaurant (
	restaurant_id int NOT NULL AUTO_INCREMENT,
	restaurant_name varchar(256) NOT NULL,
	street varchar(128),
	city varchar(60),
	state varchar(40),
	zip varchar(20),
	PRIMARY KEY (restaurant_id)
);

CREATE TABLE patron (
	patron_id int NOT NULL AUTO_INCREMENT,
	patron_first_name varchar(60) NOT NULL,
	patron_last_name varchar(60) NOT NULL,
	patron_food_preference varchar(128),
	PRIMARY KEY (patron_id)
);

CREATE TABLE employee (
	employee_id int NOT NULL AUTO_INCREMENT,
	employee_first_name varchar(60) NOT NULL,
	employee_last_name varchar(60) NOT NULL,
	employee_job_title varchar(80),
	restaurant_id int NULL,
	PRIMARY KEY (employee_id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id) ON DELETE CASCADE
);

CREATE TABLE restaurant_patron (
	patron_id int NOT NULL,
	restaurant_id int NOT NULL,
	FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id) ON DELETE CASCADE,
	FOREIGN KEY (patron_id) REFERENCES patron (patron_id) ON DELETE CASCADE
);