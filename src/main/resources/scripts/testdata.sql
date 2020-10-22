INSERT INTO unit (label) VALUES ('g');
INSERT INTO unit (label) VALUES ('kg');
INSERT INTO unit (label) VALUES ('stück');

INSERT INTO recipe (name) VALUES ('3-Zutaten-Erdnussbutterkekse');

INSERT INTO ingredient (name, common) VALUES ('Erdnussbutter', false);
INSERT INTO ingredient (name, common) VALUES ('Zucker', true);
INSERT INTO ingredient (name, common) VALUES ('Eier', false);

INSERT INTO recipeingrendient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 1, 240, 1);
INSERT INTO recipeingrendient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 2, 100, 1);
INSERT INTO recipeingrendient (recipe_id, ingredient_id, amount, unit_id) VALUES (1, 3, 1, 3);

INSERT INTO inventory_entry (inventory_id, ingredient_id, amount, unit_id) VALUES (1, 1, 1000, 1);
INSERT INTO inventory_entry (inventory_id, ingredient_id, amount, unit_id) VALUES (1, 3, 5, 3);

INSERT INTO kf_user (name, inventory_id) VALUES ('Hartmut Hansen', 1)




