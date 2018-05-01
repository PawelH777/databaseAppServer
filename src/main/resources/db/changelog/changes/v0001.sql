CREATE TABLE users(
    user_id bigserial primary key,
    login text not null,
    password varchar(255) not null,
    admin boolean not null
);

create table clients(
    client_id bigserial primary key,
    firm_name text not null);

create table dimiensions(
    dimension_id bigserial primary key,
    first_dimension numeric(9,0) not null,
    second_dimension numeric(9,0) not null,
    thickness numeric(6,3) not null,
    weight numeric(6,3) not null);


create table orders(
    order_id bigserial primary key,
    metrs numeric(9,3) not null,
    materials numeric(9,3) not null,
    receive_date DATE not null,
    order_date DATE not null,
    note text);

create table ordershistory(
    order_id bigserial primary key,
    metrs numeric(9,3) not null,
    materials numeric(9,3) not null,
    receive_date DATE not null,
    order_date DATE not null,
    note text);