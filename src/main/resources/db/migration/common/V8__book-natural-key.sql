DROP table if exists book_natural;

create table book_natural (
                      isbn varchar(255),
                      publisher varchar(255),
                      title varchar(255) not null,
                      primary key (title)
) engine=InnoDB;
