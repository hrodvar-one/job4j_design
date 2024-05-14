create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('cat - Кошка', 5475, null);
insert into fauna(name, avg_age, discovery_date)
values ('fish - Карась', 1810, '1730-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('dog - Собака', 11315, null);
insert into fauna(name, avg_age, discovery_date)
values ('wolf - Волк', 5840, '1758-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('ape - Обыкновенный шимпанзе', 21915, null);
insert into fauna(name, avg_age, discovery_date)
values ('rhinoceros - Белый носорог', 18262, '1850-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('serpent - Анаконда', 10227, null);
insert into fauna(name, avg_age, discovery_date)
values ('whale - Синий кит', 40150, null);
insert into fauna(name, avg_age, discovery_date)
values ('fish - Адагумский гольян', 1825, '2024-01-01');

select * from fauna
where name LIKE '%fish%';

select * from fauna
where avg_age between '10000' and '21000';

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';