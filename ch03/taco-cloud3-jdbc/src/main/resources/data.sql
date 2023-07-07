DELETE FROM INGREDIENT_REF;
DELETE FROM INGREDIENT;
DELETE FROM TACO;
DELETE FROM TACO_ORDER;

insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('ГВДН', 'Говядина', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('ЧЕДР', 'Чеддар', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');
