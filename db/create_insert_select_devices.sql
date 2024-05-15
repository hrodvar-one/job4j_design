create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('КПК', 5000), ('Монитор', 10000), ('Смартфон', 20000), ('МФУ', 40000);
insert into people(name) values ('Максим'), ('Виктор'), ('Николай'), ('Алексей');
insert into devices_people(device_id, people_id) values (1, 4), (2, 3), (3, 2), (4, 1);
insert into devices_people(device_id, people_id) values (1, 1), (2, 2), (3, 3), (4, 4);
insert into devices_people(device_id, people_id) values (1, 3), (2, 1), (3, 2), (4, 4);

select avg(price) from devices;

select people.name as "Имя покупателя", avg(devices.price) as "Средняя цена устройств"
from devices_people
join people
on devices_people.people_id = people.id
join devices
on devices_people.device_id = devices.id
group by people.name;

select people.name as "Имя покупателя", avg(devices.price) as "Средняя цена устройств"
from devices_people
join people
on devices_people.people_id = people.id
join devices
on devices_people.device_id = devices.id
where devices.price > 5000
group by people.name;
