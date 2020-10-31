SELECT
        r.recipe_id,
        (CASE
            WHEN missingIngredients.missingIngredientCount IS NULL THEN 0
            ELSE missingIngredients.missingIngredientCount END) AS missingIngredientCount
FROM
recipe r
LEFT JOIN
(
    SELECT
        recipeIngredients_in_Inventory.recipe_id,
        COUNT(*) AS missingIngredientCount
    FROM
        (SELECT
            ri.recipe_id,
            ri.ingredient_id,
            ingredientIDsInInventory.ingredient_id AS ingredientIdInInventory
        FROM
            recipe_ingredient ri
        LEFT JOIN
            (
                SELECT
                    ie.ingredient_id
                FROM
                    kf_user u
                INNER JOIN
                    inventory_entry ie
                        ON ie.inventory_id = u.inventory_id
                WHERE
                    u.user_id = 'ZD5DU8b2qaQiYqmZAjpuK1OlLBXb41Bj@clients'
            ) AS ingredientIDsInInventory
                ON ri.ingredient_id = ingredientIDsInInventory.ingredient_id
            ) AS recipeIngredients_in_Inventory
    INNER JOIN
        ingredient i
            ON i.ingredient_id = recipeIngredients_in_Inventory.ingredient_id
    WHERE
        recipeIngredients_in_Inventory.ingredientIdInInventory IS NULL
        AND NOT i.common
    GROUP BY
        recipeIngredients_in_Inventory.recipe_id) AS missingIngredients
        ON missingIngredients.recipe_id = r.recipe_id
ORDER BY
    missingIngredientCount