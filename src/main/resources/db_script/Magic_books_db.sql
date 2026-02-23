CREATE DATABASE magicbooks_db;

USE magicbooks_db;

CREATE TABLE User (
	user_id VARCHAR(8) PRIMARY KEY,
	created_at DATE,
	is_active BOOLEAN
);

CREATE TABLE UserCredentials (
	user_cred_id VARCHAR(8) PRIMARY KEY,
	username VARCHAR(50),
	password VARCHAR(50),
    user_id VARCHAR(8),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE UserInfo (
	user_info_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	phone_number VARCHAR(15),
    address VARCHAR(250),
    user_id VARCHAR(8),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Roles (
	role_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE UserRole (
	user_role_id VARCHAR(8) PRIMARY KEY,
	is_last_login BOOLEAN,
	user_id VARCHAR(8),
    role_id VARCHAR(8),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES User(user_id),
	CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

CREATE TABLE Customer (
	customer_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	phone_number VARCHAR(15),
    address VARCHAR(250)
);

CREATE TABLE Kids (
	kid_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	dob DATE,
    customer_id VARCHAR(8),
    CONSTRAINT fk_cust_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE BookRentalReturn (
	rent_id VARCHAR(8) PRIMARY KEY,
    rent_total DECIMAL(5,2),
	issue_date DATE,
    due_date DATE,
    is_all_returned BOOLEAN,
    customer_id VARCHAR(8),
    CONSTRAINT fk_cust_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE RentDetails (
	id VARCHAR(8) PRIMARY KEY,
	rent_id VARCHAR(8),
	book_id VARCHAR(8),
    qty INT(6),
    total DECIMAL(5,2),
    return_date DATE,
    CONSTRAINT fk_rent_id FOREIGN KEY (rent_id) REFERENCES BookRentalReturn(rent_id),
	CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

CREATE TABLE Author (
	author_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	country VARCHAR(50)
);

CREATE TABLE Book (
	ISBN VARCHAR(20) PRIMARY KEY,
	title VARCHAR(100),
    category VARCHAR(50),
    rent_price Decimal(6,2),
    stock INT(5),
	author_id VARCHAR(8),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES Author(author_id)
);

CREATE TABLE BookDetails (
	book_details_id VARCHAR(8) PRIMARY KEY,
	author_id VARCHAR(8),
	ISBN VARCHAR(8),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES Author(author_id),
	CONSTRAINT fk_book_id FOREIGN KEY (ISBN) REFERENCES Book(ISBN)
);

CREATE TABLE Payment (
	ref_id VARCHAR(8) PRIMARY KEY,
	rent_id VARCHAR(8),
	payment_mode VARCHAR(50),
    bill_amount DECIMAL(5,2),
    status VARCHAR(30),
    CONSTRAINT fk_rent_id FOREIGN KEY (rent_id) REFERENCES BookRentalReturn(rent_id)
);

INSERT INTO Roles VALUES
	(1, 'Admin'),
	(2, 'Staff');




