insert into book (isbn, publisher, title, author_id) values ('12345-12345', 'EDR inc.','The Locked Room and Other Horror Stories',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('32145-12345', 'BFG inc.','The Full Monty',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('54321-12345', 'CDF inc.','The Dream and other stories',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('87652-12345', 'CCD inc.','The Doll’s House and Other Stories',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('23452-12345', 'DRF inc.','Lorna Doone',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('56422-12345', 'MKV inc.','Farewell my lovely',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('12344-12345', 'ASD inc.','Cinderella Man',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('54367-12345', 'DRE inc.','Love Actualy',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('75423-12345', 'VDR inc.','1984',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );
insert into book (isbn, publisher, title, author_id) values ('35675-12345', 'DER inc.','Women In Business',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );