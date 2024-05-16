create table car_bodies (
    id serial primary key,
	name varchar(100)
);

create table car_engines (
    id serial primary key,
    name varchar(100)
);

create table car_transmissions (
    id serial primary key,
    name varchar(100)
);

create table cars (
    id serial primary key,
    name varchar(100),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name)
values ('седан'),
       ('хэтчбек'),
       ('кабриолет'),
       ('лимузин'),
       ('универсал'),
       ('пикап'),
       ('родстер');

insert into car_engines (name)
values ('бензиновый'),
       ('дизельный'),
       ('паровой'),
       ('электрический');

insert into car_transmissions (name)
values ('механическая'),
       ('электрическая'),
       ('гидромеханическая'),
       ('паровая'),
       ('электромеханическая');

insert into cars (name, body_id, engine_id, transmission_id)
values ('BMW Z1', 3, 1, 1),
       ('Lada Vesta', 1, 1, 1),
       ('Tesla Model 3', 1, 4, 2),
       ('Rolls-Royce Phantom', 4, 1, 4);

select * from car_bodies;
select * from car_engines;
select * from car_transmissions;
select * from cars;

-- Вывести список всех машин и все привязанные к ним детали.
select cars.name as car_name,
       car_bodies.name as body_name,
       car_engines.name as engine_name,
       car_transmissions.name as transmission_name
from cars
join car_bodies on cars.body_id = car_bodies.id
join car_engines on cars.engine_id = car_engines.id
join car_transmissions on cars.transmission_id = car_transmissions.id;

-- Вывести кузова, которые не используются НИ в одной машине.
select name
from car_bodies
where id not in (select body_id from cars);

-- Вывести двигатели, которые не используются НИ в одной машине.
select name
from car_engines
where id not in (select engine_id from cars);

-- Вывести коробки передач, которые не используются НИ в одной машине.
select name
from car_transmissions
where id not in (select transmission_id from cars);