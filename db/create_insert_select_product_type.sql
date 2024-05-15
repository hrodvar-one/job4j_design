create table product
(
    id    serial primary key,
    name  varchar(255),
    type_id int,
    expired_date date,
    price   int
);

create table type
(
    id   serial primary key,
    name varchar(255)
);

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2025-01-01', 56),
                                                               ('Йогурт Danone', 6, '2024-06-01', 75),
                                                               ('Йогурт Чудо', 6, '2024-06-07', 78),
                                                               ('Snickers', 4, '2024-12-01', 78),
                                                               ('Mars', 4, '2024-11-20', 65),
                                                               ('Bounty', 4, '2024-11-12', 47),
                                                               ('Сыр моцарелла', 1, '2024-06-04', 200),
                                                               ('Сыр пармезан', 1, '2024-06-28', 450),
                                                               ('Сыр российский', 1, '2024-07-01', 350),
                                                               ('Хлеб урожайный', 5, '2024-05-17', 60),
                                                               ('Хлеб бородинский', 5, '2024-05-18', 71),
                                                               ('Хлеб белый', 5, '2024-05-19', 59),
                                                               ('Килька', 2, '2024-03-01', 190),
                                                               ('Форель', 2, '2024-05-01', 900),
                                                               ('Колбаса Докторская', 7, '2024-06-01', 270),
                                                               ('Колбаса Бутербродная', 7, '2024-06-03', 350),
                                                               ('Молоко Любинское', 3, '2024-05-24', 89),
                                                               ('Шоколад Алёнка', 4, '2024-11-01', 17),
                                                               ('Молоко Старый Молочник', 3, '2024-05-28', 200),
                                                               ('Сметана Danone', 8, '2024-05-14', 83),
                                                               ('Сметана Весёлый молочник', 8, '2024-06-04', 92),
                                                               ('Сметана Домашняя', 8, '2024-05-23', 77),
                                                               ('Ванильное мороженое', 9, '2024-09-23', 90),
                                                               ('Шоколадное мороженое', 9, '2024-08-23', 95),
                                                               ('Фруктовое мороженое', 9, '2024-10-23', 107),
                                                               ('Треска', 2, '2024-06-01', 510);

insert into product(name, type_id, expired_date, price) values ('Сыр копченый', 1, '2025-01-01', 900);

insert into type(name) values ('СЫР'),
                              ('РЫБА'),
                              ('МОЛОКО'),
                              ('ШОКОЛАД'),
                              ('ХЛЕБ'),
                              ('ЙОГУРТ'),
                              ('КОЛБАСА'),
                              ('СМЕТАНА'),
                              ('МОРОЖЕНОЕ');

select * from product
where type_id = 1;

select *
from product
where name like '%мороженое%';

select *
from product
where expired_date < '2024-05-15';

select name as "Самый дорогой продукт"
from product
where price = (select max(price) from product);

select type.name as "Имя типа", count(product.id) as "Количество"
from product
join type on product.type_id = type.id
group by type.name;

select * from product
where type_id = 1 or type_id = 3;

select type.name as "Имя типа", count(product.id) as "Количество"
from product
join type on product.type_id = type.id
group by type.name
having count(product.id) < 10;

select product.name as "Имя продукта", type.name as "Тип продукта"
from product
join type on product.type_id = type.id;

