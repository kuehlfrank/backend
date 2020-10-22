package kuehlfrank.backend.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	public static final String userInventory = "SELECT u.user_id, ie.ingredient_id FROM kf_user u"//
			+ " INNER JOIN inventory_entry ie ON u.inventory_id = ie.inventory_id"//
			+ " WHERE u.user_id = :userId ";

	public static final String unavailableIngredientAmount = "SELECT COUNT(ri.ingredient_id)"//
			+ " FROM recipe_ingredient ri"//
			+ " LEFT JOIN ( " + userInventory + " ) AS ui"//
			+ " ON ri.ingredient_id = ui.ingredient_id"//
			+ " WHERE ui.user_id IS NULL" //
			+ " AND ri.recipe_id = r.recipe_id";// ;

//	public static final String userInventory = "SELECT inv FROM "
//			+ "InventoryEntry inv ";//

	public static final String query = "SELECT * FROM recipe r"//
			+ " WHERE ("//
			+ unavailableIngredientAmount + " ) = 0"; //
//
//			// TODO
//			+ " INNER JOIN INGREDIENT i"//
//			+ " ON ri.INGREDIENT_ID = i.INGREDIENT_ID"//
//			+ " WHERE ri.RECIPE_ID = r.RECIPE_ID"//
//			+ " AND ri.INGREDIENT_ID IN (?)" //
//			+ " AND NOT i.COMMON = TRUE"//
//			+ ") = 0";

	@Query(value = query, nativeQuery = true)
	public Collection<Recipe> findPossibleRecipes(Long userId);

}
