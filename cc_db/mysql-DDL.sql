--DROP DATABASE cc_db;
CREATE DATABASE cc_db;
USE cc_db;

CREATE TABLE region (
    region_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50)
);
ALTER TABLE region ADD CONSTRAINT region_pk PRIMARY KEY (region_id);

CREATE TABLE panel (
    region_id INT NOT NULL,
    panel_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    status VARCHAR(50)
);
ALTER TABLE panel ADD CONSTRAINT panel_pk PRIMARY KEY (region_id,panel_id);

CREATE TABLE restriction (
    region_id INT NOT NULL,
    panel_id INT NOT NULL,
    restriction_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
	description VARCHAR(50),
	type VARCHAR(50)
);
ALTER TABLE restriction ADD CONSTRAINT restriction_pk PRIMARY KEY (region_id,panel_id,restriction_id);

SHOW TABLES;
