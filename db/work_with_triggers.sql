-- создаём таблицу products
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- создаём (или заменяем если уже создана) триггерную функцию discount
create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5
        AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

-- создаём триггер discount_trigger
create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

-- вставляем данные в таблицу products
insert into products (name, producer, count, price)
values ('product_3', 'producer_3', 8, 115);

-- вернём все данные из таблицы products
select * from products;

-- вставляем данные в таблицу products
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

-- отключаем триггер discount_trigger
alter table products disable trigger discount_trigger;

-- удаляем триггер discount_trigger
drop trigger discount_trigger on products;

-- создаём (или заменяем если уже создана) триггерную функцию tax
create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted)
        and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- создаём триггер tax_trigger
create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

-- отключаем триггер tax_trigger
alter table products disable trigger tax_trigger;

-- удаляем триггер discount_trigger
drop trigger tax_trigger on products;

-- вставляем данные в таблицу products. count > 5
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 9, 50);

-- создаём (или заменяем если уже создана) триггерную функцию add_tax
-- функция должна прибавлять налог к цене товара
create
or replace function add_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- создаём триггер он должен срабатывать после вставки данных,
-- для любого товара и просто насчитывать налог
-- на товар (прибавляет налог к цене товара).
-- Этот триггер будет действовать не на каждый ряд, а на запрос.
create trigger add_tax
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure add_tax();

-- вставляем данные в таблицу products для проверки add_tax
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 100);

-- отключаем триггер add_tax
alter table products disable trigger add_tax;

-- удаляем триггер add_tax
drop trigger add_tax on products;

-- создаём (или заменяем если уже создана) триггерную функцию add_tax_for_row
create
or replace function add_tax_for_row()
     returns trigger as
$$
    BEGIN
        NEW.price := NEW.price + NEW.price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- создаём триггер trigger_add_tax_before_insert_for_row
-- Триггер должен срабатывать до вставки данных
-- и насчитывать налог на товар (должен прибавить налог к цене товара).
create trigger trigger_add_tax_before_insert_for_row
    before insert
    on products
    for each row
    execute procedure add_tax_for_row();

-- вставляем данные в таблицу products для проверки trigger_add_tax_before_insert_for_row
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 5, 100);

-- отключаем триггер trigger_add_tax_before_insert_for_row
alter table products disable trigger trigger_add_tax_before_insert_for_row;

-- удаляем триггер trigger_add_tax_before_insert_for_row
drop trigger trigger_add_tax_before_insert_for_row on products;

-- создаём таблицу history_of_price
create table history_of_price (
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

-- создаём (или заменяем если уже создана) триггерную функцию add_name_price_date_in_history_of_price
create
or replace function add_name_price_date_in_history_of_price()
     returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
        VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- создаём триггер trigger_add_name_price_date_in_history_of_price
    create trigger trigger_add_name_price_date_in_history_of_price
    after insert
    on products
    for each row
    execute procedure add_name_price_date_in_history_of_price();

-- вставляем данные в таблицу products для проверки trigger_add_tax_before_insert_for_row
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 88);

-- проверяем таблицу history_of_price
select * from history_of_price;

