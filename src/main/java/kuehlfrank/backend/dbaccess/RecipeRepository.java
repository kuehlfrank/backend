package kuehlfrank.backend.dbaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	public static final String query = "SELECT r.* FROM RECIPE r"//
			+ " WHERE ("//
			+ " SELECT COUNT (ri.ID)"//
			+ " FROM RECIPEINGREDIENT ri"//
			+ " INNER JOIN INGREDIENT i"//
			+ " ON ri.INGREDIENT_ID = i.INGREDIENT_ID"//
			+ " WHERE ri.RECIPE_ID = r.RECIPE_ID"//
			+ " AND ri.INGREDIENT_ID IN (?)" //
			+ " AND NOT i.COMMON = TRUE"//
			+ ") = 0";

	@Query("SELECT r FROM Recipe r WHERE r.id = 1")
	public Collection<Recipe> testFind();

//	@Query(query)
//	public Collection<Recipe> findPossibleRecipes();

}
