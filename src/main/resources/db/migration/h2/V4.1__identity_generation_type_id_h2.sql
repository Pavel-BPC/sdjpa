DROP table if exists book;
DROP table if exists author;

create table book
(
    id identity not null,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id bigint,
    primary key (id)
);

create table author
(
    id identity not null,
    first_name varchar(255),
    last_name  varchar(255),
    primary key (id)
);