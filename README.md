## prepare

- run container

<!-- - [bcrypt](https://www.npmjs.com/package/bcrypt)
```shell
npm install -s bcrypt
node
> const bcypt = require('bcrypt');
> const rawPassword = 'hogefuga';
> bcypt.hashSync(rawPassword, 10);
'$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.'
> bcypt.compareSync(rawPassword, '$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.');
true
``` -->

- test data sample

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
#        (1,'admin@example.com', '$2a$10$hBUz.jpzVOMgLq2gPmQAvOmRewcppw/efvrExgZcfma8VmXHckTK6', 'kendrick', 'ADMIN'), -- 1qaazxsw2
#        (2,'user@example.com', '$2a$10$BYNYHWPZQzWyPAZRwLuzSO1UPTE2jTdziYjoHw8gRjn95t7zf2fs6', 'tyler', 'USER'); -- 1qazxsw2
```

## test api

you can use [postman collection](./postman)

```shell
# login
$ curl -i -c cookie.txt -H 'Content-Type:application/x-www-form-urlencoded' -X POST -d 'email=user@example.com' -d 'pass=1qazxsw2' http://localhost:8080/login

# get books with cookie
$ curl -i -b cookie.txt http://localhost:8080/books
HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 12 Nov 2021 15:02:13 GMT

{"books":[{"id":100,"title":"kotlin beginner","author":"mike","is_rental":false},{"id":200,"title":"java beginner","author":"popcorn","is_rental":false},{"id":300,"title":"scala beginner","author":"taro","is_rental":false}]}
```
