create table UserDetails(
    id INTEGER  PRIMARY KEY AUTO_INCREMENT,
    UserName varchar(20) not null,
    UserPassword varchar(100) not null,
    MobileNo number(10) not null,
    Gender varchar(1) not null,
    Age number(3) not null
);