DROP TABLE IF EXISTS INVENTORY;

CREATE TABLE INVENTORY (
  id SERIAL PRIMARY KEY,
  item_name VARCHAR(250) NOT NULL,
  price NUMERIC NOT NULL
);

CREATE TYPE GENDER as ENUM('L', 'P');

CREATE TABLE SELLER (
	id SERIAL PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	password VARCHAR NOT NULL,
	username VARCHAR(50) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	avatar BYTEA,
	birth_date DATE NOT NULL,
	gender VARCHAR(1) NOT NULL,
	vendor_type INTEGER NOT NULL,
	is_activated boolean NOT NULL,
	verification_code VARCHAR(64)
);

CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE jwt_user
(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id SERIAL PRIMARY KEY NOT NULL,
    device_id VARCHAR NOT NULL,
    jwt VARCHAR NOT NULL,
    is_invalidated boolean NOT NULL
);