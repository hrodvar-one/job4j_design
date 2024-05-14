create table family(
    id serial primary key,
    f_name varchar(255)
);

create table family_members(
    id serial primary key,
    fm_name varchar(255),
    family_id int references family(id)
);

insert into family(f_name) values ('Иванов(а)');
insert into family(f_name) values ('Петров(а)');
insert into family(f_name) values ('Сидоров(а)');
insert into family(f_name) values ('Николаев(а)');
insert into family(f_name) values ('Левин(а)');

insert into family_members(fm_name, family_id) values ('Иван', 1);
insert into family_members(fm_name, family_id) values ('Петр', 3);
insert into family_members(fm_name, family_id) values ('Степан', 1);
insert into family_members(fm_name, family_id) values ('Маша', 2);
insert into family_members(fm_name, family_id) values ('Влас', 4);

insert into family_members(fm_name, family_id) values ('Настя');

select fm.fm_name, f.f_name
from family_members as fm
join family as f on fm.family_id = f.id;

select fm.fm_name as Имя, f.f_name as Фамилия
from family_members as fm
join family as f on fm.family_id = f.id;

select fm.fm_name as "Имя члена семьи" , f.f_name "Фамилия"
from family_members as fm
join family as f on fm.family_id = f.id;

