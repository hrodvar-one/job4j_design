-- Скрипт описания урока 6. Индексы в SQL (кластерные и некластерные)
-- создаём таблицу people
create table people (
    id         serial primary key,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    phone      VARCHAR(50)
);

-- смотрим структуру созданной таблицы
\d people

-- создаём индекс для нашей таблицы для столбца last_name и укажем что сортировка будет по убыванию
create index people_last_name on people(last_name desc);

-- переименуем индекс в people_last_name_desc
alter index people_last_name RENAME to people_last_name_desc;

-- удаляем индекс
drop index people_last_name_desc;




