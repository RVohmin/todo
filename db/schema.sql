create table users
(
    id       serial primary key,
    name     varchar(100) not null,
    email    varchar(50)  not null unique,
    password varchar(100) not null
);
create table item
(
    id       serial primary key,
    describe text,
    created  timestamp,
    done     boolean,
    user_id  int not null references users (id)
);



