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
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE UserInfo (
	user_info_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	phone_number VARCHAR(15),
    address VARCHAR(250),
    user_id VARCHAR(8),
    CONSTRAINT fk_user_id_info FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Roles (
	role_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE UserRole (
	user_role_id VARCHAR(8) PRIMARY KEY,
	user_id VARCHAR(8),
    role_id VARCHAR(8),
    is_last_login BOOLEAN,
    CONSTRAINT fk_user_id_userrole FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES Roles(role_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE UserRole;

CREATE TABLE Customer (
	customer_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	phone_number VARCHAR(15),
    address VARCHAR(250)
);

SELECT * FROM Customer;

CREATE TABLE Kids (
	kid_id VARCHAR(8) PRIMARY KEY,
	name VARCHAR(50),
	dob DATE,
    customer_id VARCHAR(8),
    CONSTRAINT fk_cust_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BookRentalReturn (
	rent_id VARCHAR(8) PRIMARY KEY,
	issue_date DATE,
    due_date DATE,
    rent_total DECIMAL(5,2),
    is_all_returned BOOLEAN,
    customer_id VARCHAR(8),
    user_id VARCHAR(8),
    CONSTRAINT fk_cust_id_rent FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_id_rent FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- DROP TABLE BookRentalReturn;
Select * from BookRentalReturn;

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
    stock INT,
	author_id VARCHAR(8),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES Author(author_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- DROP TABLE book;

CREATE TABLE BookDetails (
	book_details_id VARCHAR(8) PRIMARY KEY,
	author_id VARCHAR(8),
	ISBN VARCHAR(8),
    CONSTRAINT fk_author_id_details FOREIGN KEY (author_id) REFERENCES Author(author_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_book_id FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Alter Table BookDetails Modify ISBN VARCHAR(20);

CREATE TABLE RentDetails (
	rent_id VARCHAR(8),
	ISBN VARCHAR(20),
    qty INT,
    total DECIMAL(5,2),
    return_date DATE,
    CONSTRAINT PRIMARY KEY(rent_id,ISBN),
    CONSTRAINT fk_rent_id_details FOREIGN KEY (rent_id) REFERENCES BookRentalReturn(rent_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_book_id_details FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON DELETE CASCADE ON UPDATE CASCADE
);

Select * From rentDetails;
 
-- DROP TABLE RentDetails;

CREATE TABLE Payment (
	ref_id VARCHAR(8) PRIMARY KEY,
	rent_id VARCHAR(8),
	payment_mode VARCHAR(50),
    bill_amount DECIMAL(5,2),
    status VARCHAR(30),
    CONSTRAINT fk_rent_id FOREIGN KEY (rent_id) REFERENCES BookRentalReturn(rent_id) ON DELETE CASCADE ON UPDATE CASCADE
);

SELECT * FROM PAyment;

-- DROP TABLE Payment;

INSERT INTO Roles VALUES
	('1', 'Admin'),
	('2', 'Staff');

INSERT INTO User VALUES
	('U001','2023-01-04',true),
	('U002','2023-02-24',true);
    
INSERT INTO UserRole VALUES
	('UR001','U001','1',true),
	('UR002','U001','2',false),
    ('UR003','U002','2',true);

INSERT INTO UserCredentials VALUES
	('UC001','U001','123','U001'),
	('UC002','U002','321','U002');

INSERT INTO UserInfo VALUES
	('UI001','Saman','0752683710','Panadura','U001'),
	('UI002','Rukshi','0775031810','Colombo','U002');

SELECT * FROM RentDetails;

INSERT INTO Customer VALUES
	('C0001','Jayani','0713456213','Kalutara'),
	('C0002','Mahesh','0774590888','Kelaniya');

INSERT INTO Author VALUES
	('A0001','Eric Carle','United States'),
	('A0002','Sam McBratney','United Kingdom'),
    ('A0003','Bill Martin Jr.','United States');
 
 INSERT INTO Book VALUES
	('0-399-22690-7 (US)','The Very Hungry Catepillar','Children',150.00,10,'A0001'),
	('978-0805087185','Brown Bear Brown Bear What Do You See?','Children',150.00,15,'A0003'),
    ('978-0763642648','Guess How much I love you','Children',150.00,5,'A0002');
    
Select * from BookRentalReturn;

INSERT INTO RentDetails VALUES
	('R0001','978-0763642648',1,150.00,null);
    
UPDATE RentDetails SET return_date='2026-02-28' WHERE rent_id='R0001';

select * from book;

INSERT INTO Author VALUES
	('A0004','Tetsuko Kuroyanagi','Japan');