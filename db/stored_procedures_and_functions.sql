-- создаём таблицу products
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- добавляем хранимую процедуру, которая позволит вставлять данные в таблицу products
create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

-- вызываем хранимую процедуру
call insert_data('product_2', 'producer_2', 15, 32);

-- проверяем, что в таблице products появилось новое значение
select * from products;

-- добавим процедуру для обновления данных в таблице products
create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
        end if;
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;

-- вызываем процедуру для обновления данных
call update_data(10, 0, 1);

-- проверяем, что в таблице products появилось новое значение
select * from products;

-- добавим ещё несколько записей в таблицу products
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

-- увеличим все цены на сумму налога
call update_data(0, 0.2, 0);

-- переименуем процедуру
alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

-- удалить процедуру можно следующей командой
drop procedure update_data(u_count integer, tax float, u_id integer);

-- зачищаем таблицу products перед использованием хранимых функций:
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

-- создаём хранимую функцию
create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

-- вызываем хранимую функцию
select f_insert_data('product_1', 'producer_1', 25, 50);

-- создаём хранимую функцию для редактирования данных в таблице products
create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select into result sum(price)
            from products;
        end if;
        return result;
    end;
$$;

-- вызываем хранимую функцию для редактирования данных
select f_update_data(10, 0, 1);

-- добавим ещё несколько записей в таблицу products
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

-- выполним запрос на обновление цен продуктов с учетом налога
select f_update_data(0, 0.2, 0);

-- создадим процедуру для удаления данных из таблицы products
create
or replace procedure p_delete_data(u_id integer)
language 'plpgsql'
as
$$
    begin
        delete from products
        where id = u_id;
    end;
$$;

-- добавим ещё несколько записей в таблицу products
select f_insert_data('product_2', 'producer_2', 0, 32);
select f_insert_data('product_3', 'producer_3', 2, 14);
select f_insert_data('product_1', 'producer_1', 4, 48);
select f_insert_data('product_4', 'producer_4', 1, 56);

-- вызываем процедуру для удаления данных
call p_delete_data(1);

-- создадим функцию для удаления данных из таблицы products
create
or replace function f_delete_data(u_id integer)
returns integer
language 'plpgsql'
as
$$
    begin
        delete from products
        where id = u_id and count = 0;
        return 0;
    end;
$$;

-- вызываем функцию для удаления данных
select f_delete_data(13);