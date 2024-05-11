create table family(
    id serial primary key,
    f_name varchar(255)
);

create table family_members(
    id serial primary key,
    fm_name varchar(255),
    family_id int references family(id)
);

insert into family(f_name) values ('Ивановы');
insert into family_members(fm_name, family_id) VALUES ('Иван', 1);

select * from family_members;

select * from family where id in (select family_id from family_members);