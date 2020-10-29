package kuehlfrank.backend.repositories;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.model.RecipeResponse;

public interface RecipeRepository /*extends CrudRepository<Recipe, UUID>*/ {

	public static final String userInventory = "SELECT ie.ingredient_id FROM kf_user u"//
			+ " INNER JOIN inventory_entry ie ON u.inventory_id = ie.inventory_id"//
			+ " WHERE u.user_id = :userId ";

	public static final String unavailableIngredientAmount = "SELECT r.*, missing_ingredients.count FROM"
			+ " ( SELECT COUNT(ri.ingredient_id) AS count, ri.recipe_id"//
			+ " FROM recipe_ingredient ri"//
			+ " LEFT JOIN ( " + userInventory + " ) AS ui"//
			+ " ON ri.ingredient_id = ui.ingredient_id"//
			+ " INNER JOIN ingredient i"//
			+ " ON ri.ingredient_id = i.ingredient_id"//
			+ " WHERE ui.ingredient_id IS NULL" //
			+ " AND NOT i.common"
			+ " GROUP BY ri.recipe_id ) missing_ingredients" //
			+ " INNER JOIN recipe r" //
			+ " ON r.recipe_id = missing_ingredients.recipe_id"
		;// ;

	public static final String query = "SELECT * FROM recipe r"//
			+ " WHERE (" + unavailableIngredientAmount + ") = 0"; //
	public static final String findPossibleRecipesQuery = "SELECT r.* FROM recipe r"//
			+ " WHERE ("+ unavailableIngredientAmount + ") = 0"; //

	public static final String bestMatchingRecipesQuery = "SELECT missing_ingredients FROM recipe r"//
			+ " INNER JOIN ("+ unavailableIngredientAmount + ") AS unavailable_ingredients" //
					+ " ON r.recipe_id = missing_ingredients.recipe_id"//
			; //

	
	@Query(value = findPossibleRecipesQuery, nativeQuery = true)
	public Collection<Recipe> findPossibleRecipes(String userId);

	@Query(value = "SELECT * FROM recipe r ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public Recipe getRandomRecipe();

}
