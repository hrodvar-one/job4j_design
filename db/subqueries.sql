-- Скрипт описания урока 9. Подзапросы
-- создаём таблицу companies
CREATE TABLE companies
(
    id   int primary key,
    city text
);

-- создаём таблицу goods
CREATE TABLE goods
(
    id         int primary key,
    name       text,
    company_id int references companies (id),
    price      int
);

-- создаём таблицу sales_managers
CREATE TABLE sales_managers
(
    id          int primary key,
    last_name   text,
    first_name  text,
    company_id  int references companies (id),
    manager_fee int
);

-- создаём таблицу managers
CREATE TABLE managers
(
    id         int primary key,
    company_id int references companies (id)
);

-- заполняем таблицу companies
INSERT INTO companies
VALUES (1, 'Москва'),
       (2, 'Нью-Йорк'),
       (3, 'Мюнхен');

-- заполняем таблицу goods
INSERT INTO goods
VALUES (1, 'Небольшая квартира', 3, 5000),
       (2, 'Квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);

-- заполняем таблицу sales_managers
INSERT INTO sales_managers
VALUES (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);

-- заполняем таблицу managers
INSERT INTO managers
VALUES (1, 2),
       (2, 3),
       (4, 1);

-- получаем информацию только о тех менеджерах по продажам,
-- которые в прошлом месяце получили вознаграждение выше среднего
SELECT * FROM sales_managers
WHERE manager_fee > (SELECT AVG(manager_fee) FROM sales_managers);

-- скалярный подзапрос
-- выводим рядом с ценой товара отображалась средняя цена всех наших товаров
SELECT name AS real_estate, price, (SELECT AVG(price) FROM goods) AS avg_price FROM goods;

-- многострочный подзапрос
-- выводим среднее вознаграждение для менеджеров, которые не внесены в таблицу managers
SELECT AVG(manager_fee)
FROM sales_managers WHERE sales_managers.id NOT IN (SELECT managers.id FROM managers);

-- коррелированный подзапрос
-- выводим количество товаров в каждой из наших компаний
SELECT city,
       (SELECT count(*)
        FROM goods as g
        WHERE c.id = g.company_id) as total_goods
FROM companies c;

-- используем JOIN для вместо подзапроса для предыдущего примера
SELECT c.city, COUNT(g.name) AS total_goods
FROM companies c
         JOIN goods g ON c.id = g.company_id
GROUP BY c.city;

-- выводим информацию о тех менеджерах по продажам,
-- чье вознаграждение было равно или выше среднего вознаграждения
-- для их компании
SELECT last_name, first_name, manager_fee
FROM sales_managers sm1
WHERE sm1.manager_fee >= (SELECT AVG(manager_fee)
                          FROM sales_managers sm2
                          WHERE sm2.company_id = sm1.company_id);

-- выводим список id компаний средняя цена товаров в которой выше,
-- чем половина максимальной цены среди цен всех товаров
SELECT company_id, AVG(price) AS average_price
FROM goods
GROUP BY company_id
HAVING AVG(price) > (SELECT MAX(price) FROM goods) / 2;

-- Выполняем задание для урока
-- создаём таблицу customers
CREATE TABLE customers (
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

-- добавляем данные в таблицу customers
INSERT INTO customers(first_name, last_name, age, country)
VALUES ('John', 'Doe', 25, 'Великобритания'),
       ('Victor', 'Jonson', 26, 'Мексика'),
       ('Jane', 'Smith', 20, 'USA'),
       ('Иван', 'Иванов', 25, 'Россия'),
       ('Василий', 'Николаев', 35, 'Канада'),
       ('John', 'Smith', 21, 'USA'),
       ('Maria', 'White', 23, 'Australia'),
       ('Михаил', 'Галустян', 25, 'Кипр'),
       ('Алла', 'Пугачева', 20, 'Израиль');

-- возвращаем список клиентов, возраст которых является минимальным
-- среди всех клиентов из США
SELECT *
FROM customers
WHERE country = 'USA'
ORDER BY age
LIMIT 1;

-- возвращаем список клиентов, возраст которых является максимальным
SELECT *
FROM customers
WHERE age = (SELECT MAX(age) FROM customers);

-- создаём таблицу orders
CREATE TABLE orders (
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

-- добавляем данные в таблицу orders
INSERT INTO orders(amount, customer_id)
VALUES (200, 2),
       (300, 3),
       (400, 4),
       (500, 5),
       (600, 6),
       (700, 7),
       (800, 8);

-- возвращаем список клиентов, которые ещё не выполнили ни одного заказа
SELECT *
FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);