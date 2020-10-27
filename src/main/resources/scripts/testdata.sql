INSERT INTO unit (label) VALUES ('g');
INSERT INTO unit (label) VALUES ('kg');
INSERT INTO unit (label) VALUES ('stück');

INSERT INTO recipe (name) VALUES ('3-Zutaten-Erdnussbutterkekse');
INSERT INTO recipe (name) VALUES ('Mehlige Erdnussbutterkekse');
INSERT INTO recipe (name) VALUES ('Erdnussbutter mit Ei');

INSERT INTO ingredient (name, common) VALUES ('Erdnussbutter', false);
INSERT INTO ingredient (name, common) VALUES ('Zucker', true);
INSERT INTO ingredient (name, common) VALUES ('Eier', false);
INSERT INTO ingredient (name, common) VALUES ('Mehl', false);

INSERT INTO ingredient_alternative_name (name, ingredient_id) VALUES ('Peanut Butter', 1);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 1, 240, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 2, 100, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 3, 1, 3);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (2, 1, 240, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (2, 2, 100, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (2, 3, 1, 3);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (2, 4, 250, 1);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (3, 1, 240, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit_id) VALUES (3, 3, 2, 3);


INSERT INTO inventory (inventory_id) VALUES (1);

INSERT INTO inventory_entry (inventory_id, ingredient_id, amount, unit_id) VALUES (1, 1, 1000, 1);
INSERT INTO inventory_entry (inventory_id, ingredient_id, amount, unit_id) VALUES (1, 3, 5, 3);

INSERT INTO kf_user (USER_ID, name, inventory_id) VALUES ('auth0|123ea12feac42s', 'Hartmut Hansen', 1)




