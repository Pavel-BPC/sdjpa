DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;

create table author
(
    id        bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine=InnoDB;

create table book
(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine=InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('Pavel', 'Blinets');

insert into book (isbn, publisher, title, author_id) values ('12345-12345', 'BPS inc.','Wolf in bowl',
                                                             (select id from author where first_name = 'Pavel' and last_name = 'Blinets') );