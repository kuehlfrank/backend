package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.RecipeResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class RecipeJpaRepositoryImpl implements RecipeJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public static final String bestMatchesQueryJPQL = "SELECT new kuehlfrank.backend.model.RecipeResponse(" +
			"r.recipeId, r.name, r.external_img_src_url, r.external_source, " +
			"(0.5 * ((count(r.recipeId) - count(ie.ingredient.ingredientId) - sum(case when i.common = true then 1 else 0 end)) + abs(count(r.recipeId) - count(ie.ingredient.ingredientId) - sum(case when i.common = true then 1 else 0 end)))) AS missingIngredientCount" +
			") FROM Recipe r " +
			"left join RecipeIngredient ri on ri.recipeIngredientId.recipeId = r.recipeId " +
			"inner join User u on u.userId = :userId " +
			"left join InventoryEntry ie on ie.inventory.inventoryId = u.inventory.inventoryId and ri.ingredient.ingredientId = ie.ingredient.ingredientId " +
			"inner join Ingredient i on i.ingredientId = ri.ingredient.ingredientId " +
			"group by r.recipeId, r.name, r.external_img_src_url, r.external_source " +
			"order by missingIngredientCount, r.recipeId";

	public Collection<RecipeResponse> findBestMatchingRecipes(String userId) {
		TypedQuery<RecipeResponse> query = entityManager.createQuery(bestMatchesQueryJPQL, RecipeResponse.class).setParameter("userId", userId);
		return query.getResultList();
	}
}
