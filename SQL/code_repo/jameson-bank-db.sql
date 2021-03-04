
DROP TABLE IF EXISTS customer;
TRUNCATE customers CASCADE;

CREATE TABLE customers ( 
	email VARCHAR(50) PRIMARY KEY,
	customer_password VARCHAR(20) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
);


DROP TABLE IF EXISTS accounts CASCADE;


CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,
	account_name VARCHAR(50) NOT NULL,
	customer_email VARCHAR(50) NOT NULL,
	balance NUMERIC CHECK (balance>=0),
	status VARCHAR(8) NOT NULL,
	CONSTRAINT fk_customers FOREIGN KEY(customer_email) REFERENCES customers(email)
);


DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
	id SERIAL PRIMARY KEY,
	action_type VARCHAR(10) NOT NULL,
	amount NUMERIC CHECK (amount>=1),
	from_customer_email VARCHAR(50) NOT NULL,
	to_customer_email VARCHAR(50) NOT NULL,
	transaction_date DATE,
	status VARCHAR(10) NOT NULL
	
);

DROP TABLE IF EXISTS customer_mail;


CREATE TABLE customer_mail(
	time_stamp TIMESTAMP PRIMARY KEY,
	customer_email VARCHAR(50) NOT NULL,
	subject VARCHAR(100) NOT NULL,
	text_body TEXT NOT NULL,
	from_email VARCHAR(50) NOT NULL,
	to_email VARCHAR(50) NOT NULL,
	CONSTRAINT fk_customers FOREIGN KEY(from_email) REFERENCES customers(email)
);

DROP TABLE IF EXISTS employees;


CREATE TABLE employees (
	id VARCHAR (10) PRIMARY KEY,
	employee_password VARCHAR(20) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	hired_on DATE NOT NULL
);

INSERT INTO customers (email, first_name, last_name, customer_password)
VALUES
('daisyjune22@gmail.com', 'Daisy', 'June', 'daisyjune22');
INSERT INTO accounts (account_name, customer_email, balance, status)
VALUES
('Primary', 'daisyjune22@gmail.com', '0', 'approved');


SELECT *
FROM accounts;

SELECT * FROM jameson_bank.customers WHERE email  = 'johndiggy@gmail.com' AND customer_password = 'jdig1234';

INSERT INTO accounts (account_name, customer_email, balance, status)
VALUES
('Primary', 'johndiggy@gmail.com', '500.00', 'approved');

SELECT balance FROM accounts WHERE customer_email = 'johndiggy@gmail.com' AND account_name = 'Primary';

DELETE FROM transactions WHERE id = 9;

--transfer code
UPDATE accounts
SET balance = 490.75
WHERE customer_email = 'johndiggy@gmail.com' AND account_name = 'Primary';
UPDATE accounts 
SET balance 56.77
WHERE customer_email = 'daisyjune22@gmail.com' AND account_name = 'Primary';

SELECT account_name
FROM accounts
WHERE customer_email ='johndiggy@gmail.com'
;

UPDATE transactions 
SET status = 'Approved', id = 7;
WHERE status = 'Pending' AND to_customer_email = 'johndiggy@gmail.com' AND id = 9;

INSERT INTO employees (id, employee_password, first_name, last_name, hired_on)
VALUES
('1234', '1234','Claire', 'Jenkins', '2020-03-03');

SELECT * FROM jameson_bank.accounts WHERE account_id = 8;

SELECT * FROM jameson_bank.transactions WHERE to_customer_email = 'daisyjune22@gmail.com';

SELECT amount FROM jameson_bank.transactions WHERE to_customer_email = 'daisyjune22@gmail.com' AND id =3;

SELECT * FROM jameson_bank.accounts WHERE status = 'Pending' ;

UPDATE accounts
SET status = 'Approved'
Where status = 'approved';
