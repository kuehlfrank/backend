SELECT r.recipe_id,
       ri.ingredient_id,
       ie.ingredient_id,
       i.common
FROM recipe r
         LEFT JOIN recipe_ingredient ri on r.recipe_id = ri.recipe_id

         INNER JOIN kf_user u ON u.user_id = 'ZD5DU8b2qaQiYqmZAjpuK1OlLBXb41Bj@clients'
         LEFT JOIN inventory_entry ie ON ie.inventory_id = u.inventory_id AND ri.ingredient_id = ie.ingredient_id

         INNER JOIN ingredient i ON i.ingredient_id = ri.ingredient_id
WHERE
  r.recipe_id in ('ecfd8679-2ec8-4376-a28f-0c0ce8a90959', '3212813d-473b-4fcb-b17f-3f98f1ee8149', '8b4f2fad-53be-4a6a-8d29-d001e6a6612c')
ORDER BY r.recipe_id;
-- '3212813d-473b-4fcb-b17f-3f98f1ee8149' hier sollten 0 fehlen
-- ecfd8679-2ec8-4376-a28f-0c0ce8a90959 hier ist ein common ingredient drin (insgesamt sinds 2)


SELECT r.recipe_id,
       (0.5* ((COUNT(*) - COUNT(ie.ingredient_id) - SUM(i.common::int)) + ABS(COUNT(*) - COUNT(ie.ingredient_id) - SUM(i.common::int)))) AS missingIngredientCount -- limit to max 0 missing
FROM recipe r
         LEFT JOIN recipe_ingredient ri on r.recipe_id = ri.recipe_id

         INNER JOIN kf_user u ON u.user_id = 'ZD5DU8b2qaQiYqmZAjpuK1OlLBXb41Bj@clients'
         LEFT JOIN inventory_entry ie ON ie.inventory_id = u.inventory_id AND ri.ingredient_id = ie.ingredient_id

         INNER JOIN ingredient i ON i.ingredient_id = ri.ingredient_id
GROUP BY r.recipe_id
ORDER BY missingIngredientCount, r.recipe_id
LIMIT 10 OFFSET 0;

-- '3212813d-473b-4fcb-b17f-3f98f1ee8149' hier sollten 0 fehlen
-- ecfd8679-2ec8-4376-a28f-0c0ce8a90959 hier ist ein common ingredient drin (insgesamt sinds 2)


