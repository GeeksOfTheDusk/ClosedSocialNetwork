# Invitation key scheme

# --- !Ups

CREATE TABLE InvitationKey (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	key_string varchar(10) NOT NULL,
	creator_id bigint(20) NOT NULL,
 	PRIMARY KEY (id)
);

CREATE TABLE Relationship (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    from_id bigint(20) NOT NULL,
    to_id bigint(20) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE InvitationKey;