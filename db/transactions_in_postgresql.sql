-- Скрипт описания урока 4.4. Транзакции. Работа с транзакциями в PostgreSQL
-- создаём таблицу products в PostgreSQL
create table products (
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- добавляем данные в таблицу
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

-- выполняем транзакцию
begin transaction;

-- выполним вставку ещё одной записи в таблицу
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

-- зафиксируем изменения в транзакции
commit transaction;

-- выполним проверку таблицы после транзакции
select * from products;

-- начнём новую транзакцию
begin transaction;

-- выполним удаление всех записей из таблицы
delete from products;

-- удалим таблицу из БД
drop table products;

-- выполняем откат изменений
rollback transaction;

-- выполним проверку таблицы после отката транзакции
select * from products;

-- запустим новую транзакцию
begin transaction;

-- вставим еще одну запись в нашу таблицу
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

-- добавим точку сохранения в нашу транзакцию
savepoint first_savepoint;

-- выполним в транзакции несколько команд – UPDATE, DELETE
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

-- делаем выборку всех записей из таблицы products
select * from products;

-- выполним ROLLBACK до точки сохранения, которую мы добавили ранее
rollback to first_savepoint;

-- повторно выполним выборку всех данных из таблицы products
select * from products;

-- зафиксируем все изменения, которые были внесены в нашей транзакции с учетом отката изменений до точки сохранения
commit transaction;

-- Выполняем задание для урока
-- начнём новую транзакцию
begin transaction;

-- выполним вставку ещё одной записи в таблицу
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

-- выполним проверку таблицы
select * from products;

-- создаём первую точку сохранения
savepoint first_savepoint;

-- выполним удаление всех записей из таблицы
delete from products;

-- выполним проверку таблицы
select * from products;

-- выполним ROLLBACK до первой точки сохранения, которую мы добавили ранее
rollback to first_savepoint;

-- выполним вставку ещё одной записи в таблицу
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 10, 61);

-- создаём вторую точку сохранения
savepoint second_savepoint;

-- выполним ROLLBACK до первой точки сохранения, которую мы добавили ранее
rollback to first_savepoint;

-- выполним проверку таблицы
select * from products;





