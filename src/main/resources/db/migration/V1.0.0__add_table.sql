create table user
(
    id        bigint              not null,
    email     varchar(256) unique not null,
    password  varchar(128)        not null,
    name      varchar(32)         not null,
    role_type enum ('ADMIN', 'USER'),
    primary key (id)
)
    engine = InnoDB
    default charset = UTF8MB4;

create table book
(
    id           bigint       not null,
    title        varchar(128) not null,
    author       varchar(32)  not null,
    release_date date         not null,
    primary key (id)
)
    engine = InnoDB
    default charset = UTF8MB4;

create table rental
(
    book_id         bigint   not null,
    user_id         bigint   not null,
    rental_datetime datetime not null,
    return_Deadline datetime not null,
    primary key (book_id)
)
    engine = InnoDB
    default charset = UTF8MB4;
