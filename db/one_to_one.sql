create table inn(
    id serial primary key,
    inn_number bigint
);

create table people(
    id serial primary key,
    p_name varchar(255),
    inn_id int references inn(id)
);

insert into inn(inn_number) values (532117575321);
insert into people(p_name, inn_id) values ('Иван', 1);

select * from people;

select * from inn where id in (select inn_id from people);