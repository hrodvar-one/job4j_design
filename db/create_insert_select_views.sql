-- создаём таблицу с названиями производителей
create table manufacturers (
    id serial primary key,
    name text
);

-- создаём таблицу с названиями машин
create table cars (
    id serial primary key,
    name text,
    manufacturer_id integer references manufacturers(id)
);

-- создаём таблицу с именами покупателей
create table customers (
    id serial primary key,
    name text
);

-- создаём таблицу c заказами
create table orders (
    id serial primary key,
    active boolean default true,
    car_id integer references cars(id),
    customer_id integer references customers(id)
);

-- заполняем значениями таблицу с названиями производителей
insert into manufacturers (name)
values ('BMW'),
       ('Ford'),
       ('Toyota'),
       ('Honda'),
       ('Volkswagen'),
       ('Audi'),
       ('Mercedes'),
       ('Skoda'),
       ('Volvo'),
       ('Chevrolet'),
       ('Dodge'),
       ('Chrysler'),
       ('Lamborghini'),
       ('DMC'),
       ('Автоваз'),
       ('Aurus Motors'),
       ('Уралвагонзавод'),
       ('IFA');

-- заполняем значениями таблицу с названиями машин
insert into cars (name, manufacturer_id)
values ('BMW X5', 1),
       ('Ford Focus', 2),
       ('Toyota Prius', 3),
       ('Honda Civic', 4),
       ('Volkswagen Golf', 5),
       ('Audi R8', 6),
       ('Mercedes-Benz E-Class', 7),
       ('Skoda Octavia', 8),
       ('Volvo S60', 9),
       ('Chevrolet Camaro', 10),
       ('Dodge Charger', 11),
       ('Chrysler Imperial', 12),
       ('Lamborghini Aventador', 13),
       ('DeLorean DMC-12', 14),
       ('Lada 2112', 15),
       ('Aurus Komendant', 16),
       ('Т-34', 17),
       ('Brutsch Mopetta', 18);

-- заполняем значениями таблицу с именами покупателей
insert into customers (name)
values ('Андрей'),
       ('Сергей'),
       ('Костя'),
       ('Валерий Фёдорович'),
       ('Влас'),
       ('Игорь'),
       ('Вася'),
       ('Алексей'),
       ('Влад'),
       ('Дима'),
       ('Саша'),
       ('Армен');

-- заполняем значениями таблицу c заказами
insert into orders (car_id, customer_id)
values (13, 4),
       (14, 5),
       (15, 2),
       (16, 5),
       (17, 1),
       (3, 10),
       (4, 6),
       (5, 7),
       (1, 12),
       (7, 3),
       (18, 1),
       (2, 11);

-- выполняем запрос на список покупателей у которых две и менее машин одной марки
select customers.name, count(manufacturers.name), manufacturers.name
from customers
    join orders on customers.id = orders.customer_id
    join cars on orders.car_id = cars.id
    join manufacturers on cars.manufacturer_id = manufacturers.id
group by (customers.name, manufacturers.name)
having count(manufacturers.name) < 3;

-- создаём представление для предыдущего запроса
create view customers_with_orders
as
select customers.name as "Имя покупателя", count(manufacturers.name), manufacturers.name as "Марка машины"
from customers
    join orders on customers.id = orders.customer_id
    join cars on orders.car_id = cars.id
    join manufacturers on cars.manufacturer_id = manufacturers.id
group by (customers.name, manufacturers.name)
having count(manufacturers.name) < 3;

-- выполняем представление
select * from customers_with_orders;

-- изменяем представление
alter view customers_with_orders rename to customers_with_orders_new;

-- удаляем представление
drop view customers_with_orders_new;