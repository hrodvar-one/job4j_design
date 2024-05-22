-- Скрипт описания урока 5. Курсор в SQL
-- создаём таблицу products
create table products (
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

-- вставляем данные в таблицу products
insert into products (name, count, price)
VALUES ('product_1', 1, 5);
insert into products (name, count, price)
VALUES ('product_2', 2, 10);
insert into products (name, count, price)
VALUES ('product_3', 3, 15);
insert into products (name, count, price)
VALUES ('product_4', 4, 20);
insert into products (name, count, price)
VALUES ('product_5', 5, 25);
insert into products (name, count, price)
VALUES ('product_6', 6, 30);
insert into products (name, count, price)
VALUES ('product_7', 7, 35);
insert into products (name, count, price)
VALUES ('product_8', 8, 40);
insert into products (name, count, price)
VALUES ('product_9', 9, 45);
insert into products (name, count, price)
VALUES ('product_10', 10, 50);
insert into products (name, count, price)
VALUES ('product_11', 11, 55);
insert into products (name, count, price)
VALUES ('product_12', 12, 60);
insert into products (name, count, price)
VALUES ('product_13', 13, 65);
insert into products (name, count, price)
VALUES ('product_14', 14, 70);
insert into products (name, count, price)
VALUES ('product_15', 15, 75);
insert into products (name, count, price)
VALUES ('product_16', 16, 80);
insert into products (name, count, price)
VALUES ('product_17', 17, 85);
insert into products (name, count, price)
VALUES ('product_18', 18, 90);
insert into products (name, count, price)
VALUES ('product_19', 19, 95);
insert into products (name, count, price)
VALUES ('product_20', 20, 100);

-- запускаем транзакцию, объявив наш курсор
BEGIN;
DECLARE
cursor_products cursor for
select * from products;

-- выгрузим 10 строк, вместо 20
fetch 10 from cursor_products;

-- прочитаем ещё несколько строк с направлением NEXT
FETCH NEXT FROM cursor_products;
FETCH NEXT FROM cursor_products;

-- переместим курсор на 2 строки вперед
MOVE FORWARD 2 FROM cursor_products;

-- читаем следующую строку
FETCH NEXT FROM cursor_products;

-- закрываем курсор
CLOSE cursor_products;

-- корретно закрываем транзакцию
COMMIT;

-- Выполняем задание для урока
-- создаём таблицу products
create table products (
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

-- вставляем данные в таблицу products
insert into products (name, count, price)
VALUES ('product_1', 1, 5);
insert into products (name, count, price)
VALUES ('product_2', 2, 10);
insert into products (name, count, price)
VALUES ('product_3', 3, 15);
insert into products (name, count, price)
VALUES ('product_4', 4, 20);
insert into products (name, count, price)
VALUES ('product_5', 5, 25);
insert into products (name, count, price)
VALUES ('product_6', 6, 30);
insert into products (name, count, price)
VALUES ('product_7', 7, 35);
insert into products (name, count, price)
VALUES ('product_8', 8, 40);
insert into products (name, count, price)
VALUES ('product_9', 9, 45);
insert into products (name, count, price)
VALUES ('product_10', 10, 50);
insert into products (name, count, price)
VALUES ('product_11', 11, 55);
insert into products (name, count, price)
VALUES ('product_12', 12, 60);
insert into products (name, count, price)
VALUES ('product_13', 13, 65);
insert into products (name, count, price)
VALUES ('product_14', 14, 70);
insert into products (name, count, price)
VALUES ('product_15', 15, 75);
insert into products (name, count, price)
VALUES ('product_16', 16, 80);
insert into products (name, count, price)
VALUES ('product_17', 17, 85);
insert into products (name, count, price)
VALUES ('product_18', 18, 90);
insert into products (name, count, price)
VALUES ('product_19', 19, 95);
insert into products (name, count, price)
VALUES ('product_20', 20, 100);

-- запускаем транзакцию, объявив наш курсор
BEGIN;
DECLARE
cursor_products scroll cursor for select * from products;

-- переместим курсор на последнюю строку
FETCH LAST FROM cursor_products;

-- переместим курсор на 15 строку, т.е. на 5 строк назад
MOVE BACKWARD 5 FROM cursor_products;

-- переместим курсор на 7 строку, т.е. на 8 строк назад
MOVE BACKWARD 8 FROM cursor_products;

-- переместим курсор на 2 строку, т.е. на 5 строк назад
MOVE BACKWARD 5 FROM cursor_products;

-- переместим курсор на 1 строку, т.е. на 1 строку назад
MOVE BACKWARD 1 FROM cursor_products;

-- закрываем курсор
CLOSE cursor_products;

-- корретно закрываем транзакцию
COMMIT;





