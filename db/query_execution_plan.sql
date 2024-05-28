-- Скрипт описания урока 11. План выполнения запросов
-- создаём таблицу users
CREATE TABLE users (
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username text NOT NULL
);

-- заполним таблицу users
INSERT INTO users (username)
SELECT 'person' || n
FROM generate_series(1, 1000) AS n;

-- выполним простой план запроса с несколькими операторами
EXPLAIN SELECT * FROM users ORDER BY username;

-- выполним предыдущий план запроса с LIMIT 1
EXPLAIN SELECT * FROM users LIMIT 1;

-- выполним простой пример, с использованием тех же данных запроса
EXPLAIN SELECT count(*) FROM users;

-- посмотрим статистику планировщика
SELECT reltuples, relpages FROM pg_class WHERE relname = 'users';

-- посмотрим сравнение предполагаемого количества строк с фактическими строками,
-- возвращаемыми каждой операцией
EXPLAIN ANALYZE SELECT * FROM users ORDER BY username;

-- добавим индекс
CREATE INDEX people_username_index ON users(username);

-- выполняем EXPLAIN ANALYZE
EXPLAIN ANALYZE SELECT * FROM users ORDER BY username;

-- после массовой вставки можно запустить команду ANALYZE
ANALYZE users;

-- по желанию при выполнении запроса можно отключить информацию о стоимости операции
EXPLAIN (COSTS OFF) SELECT * FROM users LIMIT 1;