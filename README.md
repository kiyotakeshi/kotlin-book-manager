- [bcrypt](https://www.npmjs.com/package/bcrypt)

```shell
npm install -s bcrypt

node

> const bcypt = require('bcrypt');

> const rawPassword = 'hogefuga';

> bcypt.hashSync(rawPassword, 10);
'$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.'

> bcypt.compareSync(rawPassword, '$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.');
true
```

```sql
# create table user
# (
#     id        bigint              not null,
#     email     varchar(256) unique not null,
#     password  varchar(128)        not null,
#     name      varchar(32)         not null,
#     role_type enum ('ADMIN', 'USER'),
#     primary key (id)
# )
#     engine = InnoDB
#     default charset = utf8;
#
# create table book
# (
#     id           bigint       not null,
#     title        varchar(128) not null,
#     author       varchar(32)  not null,
#     release_date date         not null,
#     primary key (id)
# )
#     engine = InnoDB
#     default charset = utf8;
#
# create table rental
# (
#     book_id         bigint   not null,
#     user_id         bigint   not null,
#     rental_datetime datetime not null,
#     return_Deadline datetime not null,
#     primary key (book_id)
# )
#     engine = InnoDB
#     default charset = utf8;

# insert into book
# values (100, 'kotlin beginner', 'mike', '1999-09-09'),
#        (200, 'java beginner', 'popcorn', '1989-09-09'),
#        (300, 'scala beginner', 'taro', '2001-01-09');

# insert into user
# values
#        (1,'admin@example.com', '$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.', 'kendrick', 'ADMIN'), -- hogefuga
#        (2,'user@example.com', '$2b$10$LPv.LwIICVmzSknBIzHAROKYsbcAvmiTKqfwRjUB.jeaz0O84BXRa''', 'tyler', 'USER'); -- fugahoge
```