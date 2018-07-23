CREATE TABLE car (
    id INTEGER NOT NULL AUTO_INCREMENT
    , brand VARCHAR(128)
    , model VARCHAR(128)
    , color VARCHAR(128)
    , register_number VARCHAR(128)
    , year INTEGER
    , price INTEGER
    , owner INTEGER
    , PRIMARY KEY (id)
);

CREATE TABLE owner (
    ownerid INTEGER NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(128) ,
    lastname VARCHAR(128) ,
    PRIMARY KEY (ownerid)
);
