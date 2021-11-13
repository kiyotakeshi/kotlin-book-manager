insert into book
values (100, 'kotlin beginner', 'mike', '1999-09-09'),
       (200, 'java beginner', 'popcorn', '1989-09-09'),
       (300, 'scala beginner', 'taro', '2001-01-09');

-- plain text password is `1qaazxsw2`
-- @see src/test/kotlin/com/kiyotakeshi/bookmanage/PasswordEncodingTest.kt
insert into user
values (1, 'admin@example.com', '$2a$10$hBUz.jpzVOMgLq2gPmQAvOmRewcppw/efvrExgZcfma8VmXHckTK6', 'kendrick', 'ADMIN'),
       (2, 'user@example.com', '$2a$10$BYNYHWPZQzWyPAZRwLuzSO1UPTE2jTdziYjoHw8gRjn95t7zf2fs6', 'tyler', 'USER');
