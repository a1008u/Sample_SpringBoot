create table if not exists doma_car (
    id INTEGER NOT NULL AUTO_INCREMENT,
    brand varchar(100),
    model varchar(100),
    color varchar(100),
    register_number varchar(100),
    year INTEGER,
    price INTEGER,
    owner_id INTEGER,
    PRIMARY KEY (id)
);

create table if not exists doma_owner (
    ownerid INTEGER NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(128) ,
    lastname VARCHAR(128) ,
    car_id INTEGER,
    PRIMARY KEY (ownerid)
);

create table if not exists car (
    id   INTEGER NOT NULL AUTO_INCREMENT
    , brand VARCHAR(128)
    , model VARCHAR(128)
    , color VARCHAR(128)
    , register_number VARCHAR(128)
    , year INTEGER
    , price INTEGER
    , owner INTEGER
    , PRIMARY KEY (id)
);

create table if not exists owner (
    ownerid   INTEGER      NOT NULL AUTO_INCREMENT
    , firstname VARCHAR(128)
    , lastname VARCHAR(128)
    , PRIMARY KEY (ownerid)
);
