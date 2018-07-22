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
