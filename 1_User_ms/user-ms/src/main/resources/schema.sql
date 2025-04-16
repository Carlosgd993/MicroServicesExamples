create table if not exists users (
   id    bigint primary key,
   name  varchar(255) not null,
   email varchar(255) not null unique
);