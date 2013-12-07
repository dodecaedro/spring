insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values ('1', 'josé', 'mourinho', '555-543', 'concha espina, 1');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values ('2', 'cristiano', 'ronaldo', '555-544', 'concha espina, 2');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values ('3', 'sergio', 'ramos', '555-545', 'concha espina, 3');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values ('4', 'diego', 'lopez', '555-547', 'concha espina, 4');

insert into transfer.account ("balance", "customer_id") values ('300','1');
insert into transfer.account ("balance", "customer_id") values ('75','2');
insert into transfer.account ("balance", "customer_id") values ('250','3');
insert into transfer.account ("balance", "customer_id") values ('275','4');

insert into transfer.creditcard ("issue_date", "expiration_date", "customer_id") values ('2008-08-22 00:00:00', '2010-08-22 00:00:00', 1);
insert into transfer.creditcard ("issue_date", "expiration_date", "customer_id") values ('2010-08-22 00:00:00', '2012-08-22 00:00:00', 1);
insert into transfer.creditcard ("issue_date", "expiration_date", "customer_id") values ('2008-07-15 00:00:00', '2010-07-15 00:00:00', 2);
insert into transfer.creditcard ("issue_date", "expiration_date", "customer_id") values ('2012-09-06 00:00:00', '2014-09-06 00:00:00', 4);