-- Скрипт описания урока 10. Операторы множества в SQL
-- создаём таблицу customer
CREATE TABLE customer (
    first_name text,
    last_name  text
);

-- создаём таблицу employee
CREATE TABLE employee (
    first_name text,
    last_name  text
);

-- заполняем таблицу customer и employee
INSERT INTO customer
VALUES ('Иван', 'Иванов'),
       ('Петр', 'Сергеев'),
       ('Ирина', 'Бросова'),
       ('Анна', 'Опушкина'),
       ('Потап', 'Потапов');

INSERT INTO employee
VALUES ('Кристина', 'Позова'),
       ('Михаил', 'Кругов'),
       ('Анна', 'Опушкина'),
       ('Иван', 'Иванов'),
       ('Сергей', 'Петров');

-- выполняем запрос с использованием UNION для выбора данных из таблицы customer и employee
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee;

-- упорядочиваем предыдущий запрос через ORDER BY
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
ORDER BY first_name, last_name;

-- добавляем WHERE
SELECT first_name, last_name
FROM customer
WHERE status = 'Active'
UNION
SELECT first_name, last_name
FROM employee
WHERE emp_status = 'Current'
ORDER BY first_name, last_name;

-- выполним JOIN для ранее созданных таблиц сотрудников и клиентов
SELECT e.first_name,
       e.last_name,
       c.first_name,
       c.last_name
FROM employee e
         INNER JOIN customer c
                    ON e.first_name = c.first_name
                        AND e.last_name = c.last_name;

-- повторим запрос с использованием UNION
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee;

-- выбираем данные из таблиц сотрудников и клиентов, используя UNION ALL
SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee;

-- находим с помощью EXCEPT все имена, которых нет в таблице сотрудников
SELECT first_name, last_name
FROM customer
EXCEPT
SELECT first_name, last_name
FROM employee;

-- находим с помощью INTERSECT все имена и фамилии, которые есть в обеих таблицах
SELECT first_name, last_name
FROM customer
INTERSECT
SELECT first_name, last_name
FROM employee;

-- создадим таблицу referrer
CREATE TABLE referrer (
    first_name text,
    last_name  text
);

-- заполним таблицу referrer
INSERT INTO referrer
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');

-- выполняем выборку всех имен из всех таблиц
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
UNION
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

-- выполняем выборку всех записей из таблиц клиенты и сотрудники,
-- но которых нет в таблице рефералов
SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee
EXCEPT
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

-- выводим все имена клиентов, а также имена сотрудников, которые не были рефералами
SELECT first_name, last_name
FROM customer
UNION ALL
(SELECT first_name, last_name
 FROM employee
 EXCEPT
 SELECT first_name, last_name
 FROM referrer)
ORDER BY first_name, last_name;

-- Выполняем задание для урока
-- создаём таблицу movie
CREATE TABLE movie (
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

-- создаём таблицу book
CREATE TABLE book (
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

-- вставляем данные в таблицу movie и book
INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- выбираем названия всех фильмов, которые сняты по книге
SELECT m.name
FROM movie m
         INNER JOIN book b
                    ON m.name = b.title;

-- выбираем все названия книг, у которых нет экранизации
SELECT b.title
FROM book b
         LEFT JOIN movie m
                   ON b.title = m.name
WHERE m.name IS NULL;

-- выбираем все уникальные названия произведений из таблиц movie и book
-- (т.е фильмы, которые сняты не по книге, и книги без экранизации)
SELECT DISTINCT m.name AS title
FROM movie m
FULL JOIN book b ON m.name = b.title
WHERE b.title IS NULL
UNION ALL
SELECT DISTINCT b.title AS title
FROM book b
FULL JOIN movie m ON m.name = b.title
WHERE m.name IS NULL;
