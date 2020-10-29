package kuehlfrank.backend.repositories;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.model.RecipeResponse;

@Repository
public class RecipeJpaRepository implements RecipeRepository {

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
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Collection<Recipe> findPossibleRecipes(String userId) {
		return null;
	}

	@Override
	public Collection<RecipeResponse> findBestMatchingRecipes(String userId) {
		System.out.println("start");

		return entityManager.createNativeQuery(unavailableIngredientAmount).setParameter("userId", userId).unwrap(org.hibernate.query.Query.class).setResultTransformer(new ResultTransformer() {

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				System.out.println("transform" + Arrays.toString(tuple));

				return new RecipeResponse((Recipe)tuple[0], (Integer)tuple[1]);
			}

			@Override
			public List transformList(List collection) {
				return collection;
			}}).getResultList();
//		List<RecipeResponse> results = query.list();
//		RecipeResponse result = results.get(0);
//		sysout
//		assertEquals("John Smith", result.getEmployeeName());
//		assertEquals("Sales", result.getDepartmentName());
	}

}
