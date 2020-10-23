DROP TABLE IF EXISTS RECIPE CASCADE;
DROP TABLE IF EXISTS INGREDIENT CASCADE;
DROP TABLE IF EXISTS RECIPE_INGREDIENT CASCADE;
DROP TABLE IF EXISTS INVENTORY_ENTRY CASCADE;
DROP TABLE IF EXISTS INVENTORY CASCADE;
DROP TABLE IF EXISTS UNIT CASCADE;
DROP TABLE IF EXISTS KF_USER CASCADE;

CREATE TABLE UNIT
(
    UNIT_ID SERIAL NOT NULL,
    LABEL VARCHAR(23) NOT NULL,
    PRIMARY KEY (UNIT_ID)
);

CREATE TABLE RECIPE
(
    RECIPE_ID SERIAL NOT NULL,
    NAME VARCHAR(64) NOT NULL,
    PRIMARY KEY (RECIPE_ID)
);

CREATE TABLE INGREDIENT
(
    INGREDIENT_ID SERIAL NOT NULL,
    NAME VARCHAR(32) NOT NULL,
    COMMON BOOLEAN NOT NULL,
    PRIMARY KEY (INGREDIENT_ID)
);

CREATE TABLE RECIPE_INGREDIENT
(
    RECIPE_ID INTEGER NOT NULL,
    INGREDIENT_ID INTEGER NOT NULL,
    AMOUNT NUMERIC(9, 3) NOT NULL,
    UNIT_ID INTEGER NOT NULL,
    FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT,
    FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE,
    FOREIGN KEY (UNIT_ID) REFERENCES UNIT,
    PRIMARY KEY (INGREDIENT_ID,RECIPE_ID)
);

CREATE TABLE INVENTORY
(
    INVENTORY_ID SERIAL NOT NULL,
    PRIMARY KEY (INVENTORY_ID)
);

CREATE TABLE INVENTORY_ENTRY
(
    INVENTORY_ENTRY_ID SERIAL NOT NULL,
    INVENTORY_ID INTEGER NOT NULL,
    INGREDIENT_ID INTEGER NOT NULL,
    AMOUNT NUMERIC(9, 3) NOT NULL,
    UNIT_ID INTEGER NOT NULL,
    FOREIGN KEY (INVENTORY_ID) REFERENCES INVENTORY,
    FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT,
    FOREIGN KEY (UNIT_ID) REFERENCES UNIT,
    PRIMARY KEY (INVENTORY_ENTRY_ID)
);



CREATE TABLE KF_USER
(
    USER_ID VARCHAR(50) NOT NULL,
    NAME VARCHAR(32) NOT NULL,
    INVENTORY_ID INTEGER NOT NULL,
    FOREIGN KEY (INVENTORY_ID) REFERENCES INVENTORY,
    PRIMARY KEY (USER_ID)
);