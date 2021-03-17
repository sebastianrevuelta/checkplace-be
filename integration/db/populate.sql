USE checkplace;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO `users` (username,password) values ("admin","$2a$10$td/nzuBju1RLz8y.YHiPz.i4fAZZLVw3AuLof6eoJAozJe1DTcuhe");
INSERT INTO `users` (username,password) values ("fischer","$2a$10$H3RqOaRYiqj5cVl7GCPNluSnl5bjuBbQMgToC1mJ4Wu/ZyptFd7Tu");
INSERT INTO `users` (username,password) values ("kasparov","$2a$10$2Yqp/7/yqlYDaot8SR.7qOL5tsT1FY2NGfCxVHG9NGjxbkGLF6JtS");
INSERT INTO `users` (username,password) values ("revuelta","$2a$10$kVbKFKLg00I0ICqI5QjHg.vw1gg85dVhIdsF2J1KhrqJxZPaAQ5yW");

DROP TABLE IF EXISTS `securing_users`;
CREATE TABLE `securing_users` (
    `username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO `securing_users` (username,password) values ("admin",SHA1("admin"));
INSERT INTO `securing_users` (username,password) values ("fischer",SHA1("fischer"));
INSERT INTO `securing_users` (username,password) values ("kasparov",SHA1("kasparov"));
INSERT INTO `securing_users` (username,password) values ("revuelta",SHA1("revuelta"));