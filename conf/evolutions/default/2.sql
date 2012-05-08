# Private Message scheme

# --- !Ups

CREATE TABLE PrivateMessage (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	authorID bigint(20) NOT NULL,
	receiverID bigint(20) NOT NULL,
	title varchar(255),
	content text NOT NULL,
	writtenAt timestamp NOT NULL,
	readAt timestamp NULL,
	FOREIGN KEY (receiverID) REFERENCES User(id),
	PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE PrivateMessage;
