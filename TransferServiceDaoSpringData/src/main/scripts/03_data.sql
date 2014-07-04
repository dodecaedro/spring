insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values (1, 'josé', 'mourinho', '555-543', 'concha espina, 1');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values (2, 'cristiano', 'ronaldo', '555-544', 'concha espina, 2');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values (3, 'sergio', 'ramos', '555-545', 'concha espina, 3');
insert into transfer.customer ("id", "firstname", "lastname", "phonenumber", "address") values (4, 'diego', 'lopez', '555-547', 'concha espina, 4');

insert into transfer.account ("id", "balance", "customer_id") values (1, '300','1');
insert into transfer.account ("id", "balance", "customer_id") values (2, '75','2');
insert into transfer.account ("id", "balance", "customer_id") values (3, '250','3');
insert into transfer.account ("id", "balance", "customer_id") values (4, '275','4');

insert into transfer.transfer ("id", "account_origin_id", "account_target_id", "amount", "transfer_date") values (1, 1, 2, 100, '2007-09-15 00:00:00 UTC');
insert into transfer.transfer ("id", "account_origin_id", "account_target_id", "amount", "transfer_date") values (2, 1, 3, 50, '2007-09-16 00:00:00 UTC');

insert into transfer.creditcard ("id", "issue_date", "expiration_date", "customer_id") values (1, '2008-08-22 00:00:00 UTC', '2010-08-22 00:00:00 UTC', 1);
insert into transfer.creditcard ("id", "issue_date", "expiration_date", "customer_id") values (2, '2010-08-22 00:00:00 UTC', '2012-08-22 00:00:00 UTC', 1);
insert into transfer.creditcard ("id", "issue_date", "expiration_date", "customer_id") values (3, '2008-07-15 00:00:00 UTC', '2010-07-15 00:00:00 UTC', 2);
insert into transfer.creditcard ("id", "issue_date", "expiration_date", "customer_id") values (4, '2012-09-06 00:00:00 UTC', '2014-09-06 00:00:00 UTC', 4);