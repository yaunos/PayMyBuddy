---------------------
-- Initialisation ---
---------------------

-- DROP DATABASE paymybuddy;
CREATE DATABASE IF NOT EXISTS paymybuddy;
USE paymybuddy;

------------------------
-- Création des tables -
------------------------

CREATE TABLE user_account
(
    email VARCHAR (255) PRIMARY KEY NOT NULL,
    password VARCHAR (255) NOT NULL,
    firstname VARCHAR (30) NOT NULL,
    lastname VARCHAR (30) NOT NULL,
    account_balance LONG
);

CREATE TABLE transaction
(
    transaction_number LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
    from VARCHAR (255),
    to VARCHAR (255),
    date DATE,
    transaction_amount LONG NOT NULL,
    transaction_fee LONG NOT NULL,
    description VARCHAR (255),
    FOREIGN KEY (from) REFERENCES user_account (email),
    FOREIGN KEY (to) REFERENCES user_account (email)

);

CREATE TABLE buddy_contact
(
    PRIMARY KEY (user_email, buddy_email),
    FOREIGN KEY (user_email) REFERENCES user_account (email),
    FOREIGN KEY (buddy_email) REFERENCES user_account (email)
);