insert into CUSTOMER (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) values ('José', 'Mourinho', '555-543', 'Concha Espina, 1');
insert into CUSTOMER (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) values ('Cristiano', 'Ronaldo', '555-544', 'Concha Espina, 2');
insert into CUSTOMER (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) values ('Sergio', 'Ramos', '555-545', 'Concha Espina, 3');
insert into CUSTOMER (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) values ('Diego', 'Lopez', '555-547', 'Concha Espina, 4');

insert into ACCOUNT (BALANCE, CUSTOMER_ID) values ('300','1');
insert into ACCOUNT (BALANCE, CUSTOMER_ID) values ('75','2');
insert into ACCOUNT (BALANCE, CUSTOMER_ID) values ('250','3');
insert into ACCOUNT (BALANCE, CUSTOMER_ID) values ('275','4');

insert into CREDITCARD (ISSUE_DATE, EXPIRATION_DATE, CUSTOMER_ID) values ('2008-08-22 00:00:00', '2010-08-22 00:00:00', 1);
insert into CREDITCARD (ISSUE_DATE, EXPIRATION_DATE, CUSTOMER_ID) values ('2010-08-22 00:00:00', '2012-08-22 00:00:00', 1);
insert into CREDITCARD (ISSUE_DATE, EXPIRATION_DATE, CUSTOMER_ID) values ('2008-07-15 00:00:00', '2010-07-15 00:00:00', 2);
insert into CREDITCARD (ISSUE_DATE, EXPIRATION_DATE, CUSTOMER_ID) values ('2012-09-06 00:00:00', '2014-09-06 00:00:00', 4);
