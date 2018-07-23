delete from doma_owner where ownerid = 2;
delete from doma_car where id = 2;
INSERT INTO doma_owner(ownerid,firstname, lastname, car_id) VALUES(2,'firsttest','lasttest',2);
INSERT INTO doma_car(id,brand, model, color, register_number, year, price, owner_id) VALUES(2,'testbrand', 'testmodel','testcolor','testnumber', 2018, 8102, 2);
