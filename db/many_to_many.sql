create table gamers(
    id serial primary key,
    nickname varchar(255)
);

create table online_games(
    id serial primary key,
    game_name varchar(255)
);

create table gamers_online_games(
    id serial primary key,
    gamer_id int references gamers(id),
    online_game_id int references online_games(id)
);

insert into gamers(nickname) values ('Zeus');
insert into online_games(game_name) values ('League of Legends');
insert into gamers_online_games(gamer_id, online_game_id) values (1, 1);

select * from gamers_online_games;

select * from gamers where id in (select gamer_id from gamers_online_games);