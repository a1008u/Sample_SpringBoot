delete from doma_owner where ownerid = 1;
delete from doma_car where id = 1;
INSERT INTO doma_owner(ownerid,firstname, lastname, car_id) VALUES(1,'firsttest','lasttest',1);
INSERT INTO doma_car(id,brand, model, color, register_number, year, price, owner_id) VALUES(1,'testbrand', 'testmodel','testcolor','testnumber', 2018, 8102, 1);
