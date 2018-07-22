INSERT INTO owner(ownerid,firstname, lastname) VALUES(1,'firsttest','lasttest');
INSERT INTO car(id,brand, model, color, register_number, year, price, owner) VALUES(1111,'testbrand', 'testmodel','testcolor','testnumber', 2018, 8102, 1);

INSERT INTO doma_owner(ownerid,firstname, lastname, car_id) VALUES(0,'firsttest','lasttest',0);
INSERT INTO doma_car(id,brand, model, color, register_number, year, price, owner_id) VALUES(0,'testbrand', 'testmodel','testcolor','testnumber', 2018, 8102, 0);

