# User scheme

# --- !Ups

CREATE TABLE User (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	username varchar(255) NOT NULL,
	hashedPW varchar(255) NOT NULL,
	sex char(1) NOT NULL,
	dateOfBirth date NOT NULL,
	dateOfDeath date NOT NULL,
	description text,
	anonym boolean NOT NULL,
	invitedBy bigint(20) NOT NULL,
	registrationDate date NOT NULL,
	lastLogin timestamp NOT NULL,
	isAdmin boolean NOT NULL,
	PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE User;
