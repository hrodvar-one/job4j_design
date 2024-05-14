create table position(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    position_id int references position(id)
);

insert into position(name) values ('programmer');
insert into position(name) values ('project manager');
insert into position(name) values ('director');

insert into employees(name, position_id) values('Boris', 1);
insert into employees(name, position_id) values('Ivan', 1);
insert into employees(name, position_id) values('Kiril', 1);
insert into employees(name, position_id) values ('Marina', 2);
insert into employees(name, position_id) values ('Pers', 3);

insert into employees(name) values ('Alexander');

select * from employees
join position on employees.position_id = position.id;