-- Скрипт описания урока 4.3. Транзакции. Уровни изолированности транзакций.
-- Тестируем Read Uncommitted уровень транзакции в MySQL.
-- создаём таблицу products в MySQL
create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

-- добавляем несколько записей в таблицу products в MySQL
insert into products (name, count, price)
values ('product_1', 3, 50);
insert into products (name, count, price)
values ('product_2', 15, 32);
insert into products (name, count, price)
values ('product_3', 8, 115);

-- устанавливаем желаемый уровень транзакций в таблице products в MySQL
set session transaction isolation level read uncommitted;

-- начинает две параллельные транзакции в консольной утилите
start transaction;

-- проверяем, какая информация имеется в наличии в начале каждой транзакции
select * from products;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

-- проверяем какую информацию видит вторая транзакция
select * from products;

-- получаем незафиксированные данные от первой транзакции
select sum(count) from products;

-- откатываем изменения первой транзакции
rollback;

-- получаем ошибочные данные во второй транзакции после отката первой транзакции
select sum(count) from products;

-- Тестируем Read Сommitted уровень транзакции в PostgreSQL.
-- создаём таблицу products в PostgreSQL
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

-- начинает две параллельные транзакции в консольной утилите
begin transaction;

-- проверяем, какая информация имеется в наличии в начале каждой транзакции
select * from products;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

-- проверяем какую информацию видит первая и вторая транзакции
select * from products;

-- зафиксируем изменения в первой транзакции
commit;

-- проверяем какие теперь данные видит вторая транзакция
select * from products;

-- Тестируем Repeatable Read уровень транзакции в PostgreSQL.
-- создаём таблицу products в PostgreSQL
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

-- начинает две параллельные транзакции в консольной утилите
begin transaction isolation level repeatable read;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

-- выполняем ту же самую операцию UPDATE той же самой строки только во второй транзакции
-- и мы видим, что выполнение операции UPDATE заблокировано и ожидает завершения первой транзакции
update products set price = 75 where name = 'product_1';

-- делаем commit в первой транзакции
commit;

-- делаем rollback в первой транзакции
rollback;

-- проверяем какие теперь данные видит вторая транзакция
select * from products;

-- Тестируем Serializable уровень транзакции в PostgreSQL.
-- создаём таблицу products в PostgreSQL
create table products (
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

-- начинает две параллельные транзакции в консольной утилите
-- уровень изолированности устанавливаем прямо во время начала транзакции
begin transaction isolation level serializable;

-- в первой транзакции находим сумму столбца count;
select sum(count) from products;

-- далее в первой же транзакции обновляем одну из записей
update products set count = 26 where name = 'product_1';

-- переходим во вторую транзакцию и выполняем запрос на нахождение суммы столбца count;
select sum(count) from products;

-- обновляем одну из записей в таблице products во второй транзакции
update products set count = 26 where name = 'product_2';

-- фиксируем изменения в обоих транзакциях
commit;

-- Выполнение задания для урока 4.3. Транзакции. Уровни изолированности транзакций.
-- добавляем произвольную таблицу cars в MySQL
create table cars (
    id serial primary key,
    model varchar(50),
    manufacturer varchar(100),
    count integer default 0,
    price integer
);

-- добавляем несколько записей в таблицу cars в MySQL
insert into cars (model, manufacturer, count, price)
values ('model_1','manufacturer_1', 3, 50);
insert into cars (model, manufacturer, count, price)
values ('model_2','manufacturer_2', 15, 32);
insert into cars (model, manufacturer, count, price)
values ('model_3','manufacturer_3', 8, 115);
insert into cars (model, manufacturer, count, price)
values ('model_4','manufacturer_4', 7, 50);

-- устанавливаем желаемый уровень транзакций в таблице products в MySQL
set session transaction isolation level read uncommitted;

-- начинает две параллельные транзакции в консольной утилите
start transaction;

-- проверяем, какая информация имеется в наличии в начале каждой транзакции
select * from cars;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into cars (model, manufacturer, count, price) VALUES ('model_5','manufacturer_5', 11, 64);
delete from cars where price = 115;
update cars set price = 75 where model ='model_2';

-- проверяем какую информацию видит вторая транзакция
select * from cars;

-- получаем незафиксированные данные от первой транзакции во второй транзакции
select sum(count) from cars;

-- откатываем изменения первой транзакции
rollback;

-- получаем ошибочные данные во второй транзакции после отката первой транзакции
select sum(count) from cars;

-- Тестируем Read Сommitted уровень транзакции в PostgreSQL.
-- создаём таблицу cars в PostgreSQL
create table cars (
    id serial primary key,
    model varchar(50),
    manufacturer varchar(100),
    count integer default 0,
    price integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into cars (model, manufacturer, count, price)
values ('model_1','manufacturer_1', 3, 50);
insert into cars (model, manufacturer, count, price)
values ('model_2','manufacturer_2', 15, 32);
insert into cars (model, manufacturer, count, price)
values ('model_3','manufacturer_3', 8, 115);
insert into cars (model, manufacturer, count, price)
values ('model_4','manufacturer_4', 7, 50);

-- начинает две параллельные транзакции в консольной утилите
begin transaction;

-- проверяем, какая информация имеется в наличии в начале каждой транзакции
select * from cars;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into cars (model, manufacturer, count, price) VALUES ('model_5','manufacturer_5', 11, 64);
delete from cars where price = 115;
update cars set price = 75 where model ='model_2';

-- проверяем какую информацию видит первая и вторая транзакции
select * from cars;

-- зафиксируем изменения в первой транзакции
commit;

-- проверяем какие теперь данные видит вторая транзакция
select * from cars;

-- Тестируем Repeatable Read уровень транзакции в PostgreSQL.
-- создаём таблицу cars в PostgreSQL
create table cars (
    id serial primary key,
    model varchar(50),
    manufacturer varchar(100),
    count integer default 0,
    price integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into cars (model, manufacturer, count, price)
values ('model_1','manufacturer_1', 3, 50);
insert into cars (model, manufacturer, count, price)
values ('model_2','manufacturer_2', 15, 32);
insert into cars (model, manufacturer, count, price)
values ('model_3','manufacturer_3', 8, 115);
insert into cars (model, manufacturer, count, price)
values ('model_4','manufacturer_4', 7, 50);

-- начинаем две параллельные транзакции в консольной утилите
begin transaction isolation level repeatable read;

-- выполняем операции INSERT, UPDATE, DELETE в первой транзакции
insert into cars (model, manufacturer, count, price) VALUES ('model_5','manufacturer_5', 11, 64);
delete from cars where price = 115;
update cars set price = 75 where model ='model_2';

-- выполняем ту же самую операцию UPDATE той же самой строки только во второй транзакции
-- и мы видим, что выполнение операции UPDATE заблокировано и ожидает завершения первой транзакции
update cars set price = 75 where model ='model_2';

-- делаем commit в первой транзакции
commit;

-- делаем rollback в первой транзакции
rollback;

-- проверяем какие теперь данные видит вторая транзакция
select * from cars;

-- Тестируем Serializable уровень транзакции в PostgreSQL.
-- создаём таблицу products в PostgreSQL
create table cars (
    id serial primary key,
    model varchar(50),
    manufacturer varchar(100),
    count integer default 0,
    price integer
);

-- добавляем данные в таблицу products в PostgreSQL
insert into cars (model, manufacturer, count, price)
values ('model_1','manufacturer_1', 3, 50);
insert into cars (model, manufacturer, count, price)
values ('model_2','manufacturer_2', 15, 32);
insert into cars (model, manufacturer, count, price)
values ('model_3','manufacturer_3', 8, 115);
insert into cars (model, manufacturer, count, price)
values ('model_4','manufacturer_4', 7, 50);

-- начинаем две параллельные транзакции в консольной утилите
-- уровень изолированности устанавливаем прямо во время начала транзакции
begin transaction isolation level serializable;

-- в первой транзакции находим сумму столбца count;
select sum(count) from cars;

-- далее в первой же транзакции обновляем одну из записей
update cars set price = 75 where model ='model_2';

-- переходим во вторую транзакцию и выполняем запрос на нахождение суммы столбца count;
select sum(count) from cars;

-- обновляем одну из записей в таблице products во второй транзакции
update cars set price = 35 where model ='model_4';

-- фиксируем изменения в обоих транзакциях
commit;