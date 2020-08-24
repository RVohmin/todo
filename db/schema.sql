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
    describe text not null,
    created  timestamp not null,
    done     boolean not null,
    user_id  int not null references users (id)
);
select item.describe, item.created, item.done, users.name from item join users on item.user_id =
                                                                                  users.id;


