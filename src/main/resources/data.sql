INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy1d@mail.com', '123', 'Jo', 'KER', 100);
INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy2d@mail.com', '456', 'Jean', 'BON', 150);
INSERT INTO `user_account` (`email`, `password`,`firstname`, `lastname`, `account_balance`) VALUES ('buddy3d@mail.com', '789', 'Jacques', 'USE', 45);


INSERT INTO `transaction` (`user_email`, `buddy_email`, `date`, `transaction_amount`,`transaction_fee`, `description`) VALUES ('buddy1@mail.com', 'buddy2@mail.com', '2023-04-01', '100', '5', 'CarPark');
INSERT INTO `transaction` (`user_email`, `buddy_email`, `date`, `transaction_amount`,`transaction_fee`, `description`) VALUES ('buddy1@mail.com', 'buddy3@mail.com', '2023-04-12', '20', '1','Fastfood');


INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy1@mail.com', 'buddy2@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy1@mail.com', 'buddy3@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy2@mail.com', 'buddy3@mail.com');
INSERT INTO `buddy_contact` (`user_email`, `buddy_email`) VALUES ('buddy3@mail.com', 'buddy2@mail.com');

