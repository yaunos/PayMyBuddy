---------------------
-- Initialisation ---
---------------------

-- CREATE TABLE IF NOT EXISTS

DROP DATABASE paymybuddy;
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
    account_balance DECIMAL (9,2)
);

CREATE TABLE transaction
(
    transaction_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    from_email VARCHAR (255),
    to_email VARCHAR (255),
    date DATE,
    transaction_amount DECIMAL (9,2) NOT NULL,
    transaction_fee DECIMAL (9,2) NOT NULL,
    description VARCHAR (255),
    FOREIGN KEY (from_email) REFERENCES user_account (email),
    FOREIGN KEY (to_email) REFERENCES user_account (email)

);

CREATE TABLE buddy_contact
(
    buddy_link_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR (255) NOT NULL,
    buddy_email VARCHAR (255) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user_account (email),
    FOREIGN KEY (buddy_email) REFERENCES user_account (email)
);

INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy1@mail.com', '123', 'Jo', 'KER', 1000);
INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy2@mail.com', '456', 'Jean', 'BON', 150);
INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy3@mail.com', '789', 'Jacques', 'USE', 45);


INSERT INTO `transaction` (`from_email`, `to_email`, `date`, `transaction_amount`,`transaction_fee`, `description`) VALUES ('buddy1@mail.com', 'buddy2@mail.com', '2023-04-01', '100', '5', 'CarPark');
INSERT INTO `transaction` (`from_email`, `to_email`, `date`, `transaction_amount`,`transaction_fee`, `description`) VALUES ('buddy1@mail.com', 'buddy3@mail.com', '2023-04-12', '20', '1','Fastfood');


INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy1@mail.com', 'buddy2@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy1@mail.com', 'buddy3@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy2@mail.com', 'buddy3@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy3@mail.com', 'buddy2@mail.com');

UPDATE user_account SET password = SHA2(password, 256);