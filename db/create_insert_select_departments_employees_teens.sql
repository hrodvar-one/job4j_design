create table departments
(
    id    serial primary key,
    name  varchar(255)
);

create table employees
(
    id   serial primary key,
    name varchar(255),
    department_id integer references departments(id)
);

insert into departments (name)
values ('Отдел продаж'),
       ('Отдел закупок'),
       ('Отдел безопасности'),
       ('Отдел информационных технологий'),
       ('Отдел сетевых технологий'),
       ('Отдел сводного планирования');

insert into employees (name, department_id)
values ('Игорь', 3),
       ('Андрей', 4),
       ('Дима', 5),
       ('Сергей', 1),
       ('Виктор', 2),
       ('Костя', null),
       ('Алексей', 1),
       ('Николай', null),
       ('Алексей', 2);

select * from departments;
select * from employees;

select * from employees e
         left join departments d on e.department_id = d.id;

select * from departments d
         right join employees e on d.id = e.department_id;

select * from employees e
         full join departments d on e.department_id = d.id;

select * from departments d
         cross join employees e;

-- находим все департаменты, в которых нет сотрудников
select * from departments d
         left join employees e on d.id = e.department_id
where e.department_id is null;

-- запросы которые выдают одинаковый результат
-- (порядок вывода колонок в эти запросах идентичный)
select d.id as department_id, d.name as department_name, e.id as employee_id, e.name as employee_name
from departments d
left join employees e on d.id = e.department_id;

select d.id as department_id, d.name as department_name, e.id as employee_id, e.name as employee_name
from employees e
right join departments d on d.id = e.department_id;

-- создать таблицу teens с атрибутами name, gender и заполнить её.
create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens (name, gender)
values ('Маша', 'female'),
       ('Андрей', 'male'),
       ('Дима', 'male'),
       ('Сергей', 'male'),
       ('Виктор', 'male'),
       ('Настя',  'female'),
       ('Алексей', 'male'),
       ('Николай', 'male'),
       ('Алёна',  'female');

-- выводим все возможные разнополые пары. исключая дублирование
select distinct t1.name as a, t2.name as b, concat(t1.name,'-',t2.name) as "a + b"
from teens as t1
cross join teens as t2
where t1.gender = 'female' and t2.gender ='male';