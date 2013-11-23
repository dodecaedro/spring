drop table ACCOUNT if exists;
drop sequence S_ACCOUNT if exists;

drop table CUSTOMER if exists;
drop sequence S_CUSTOMER if exists;

drop table CREDITCARD if exists;
drop sequence S_CREDITCARD if exists;

create table CUSTOMER (
ID integer identity primary key,
FIRSTNAME VARCHAR(100),
LASTNAME VARCHAR(100), 
PHONENUMBER VARCHAR(100),
ADDRESS VARCHAR(100)
);

create table ACCOUNT (
ID integer identity primary key, 
BALANCE integer,
CUSTOMER_ID integer unique not null,
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID)
);

create table CREDITCARD (
ID integer identity primary key,
ISSUE_DATE date,
EXPIRATION_DATE date,
CUSTOMER_ID integer not null,
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID)
);

create sequence S_ACCOUNT start with 1;
create sequence S_CUSTOMER start with 5;
create sequence S_CREDITCARD start with 1;