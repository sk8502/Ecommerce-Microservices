--create table if not exists category
--(
--    id          integer not null
--        primary key,
--    description varchar(255),
--    name        varchar(255)
--);
--
--create table if not exists product
--(
--    id                 integer          not null
--        primary key,
--    available_quantity double precision not null,
--    description        varchar(255),
--    name               varchar(255),
--    price              numeric(38, 2),
--    category_id        integer
--        constraint fk1mtsbur82frn64de7balymq9s
--            references category
--);
--
--create sequence if not exists category_seq increment by 50;
--create sequence if not exists product_seq increment by 50;


CREATE TABLE IF NOT EXISTS category (
    id INTEGER NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
    id INTEGER NOT NULL PRIMARY KEY,
    available_quantity DOUBLE PRECISION NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255),
    price NUMERIC(38, 2),
    category_id INTEGER,
    CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category
);

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
