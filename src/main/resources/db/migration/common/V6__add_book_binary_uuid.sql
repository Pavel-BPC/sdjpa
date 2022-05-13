DROP table if exists bookuuid;

create table bookuuid (
                      id binary(16) not null,
                      isbn varchar(255),
                      publisher varchar(255),
                      title varchar(255),
                      author_id bigint,
                      primary key (id)
) engine=InnoDB;

