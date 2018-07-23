select
  doma_owner.ownerid
  , doma_owner.firstname
  , doma_owner.lastname
  , doma_owner.car_id
  , doma_car.id
  , doma_car.brand
  , doma_car.model
  , doma_car.color
  , doma_car.register_number
  , doma_car.year
  , doma_car.price
  , doma_car.owner_id
from
  doma_owner
INNER JOIN
  doma_car
ON
  doma_owner.car_id = doma_car.id
