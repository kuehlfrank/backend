package kuehlfrank.backend.repositories;

import kuehlfrank.backend.dto.DetailedRecipeSuggestion;
import kuehlfrank.backend.dto.RecipeIngredientStatus;
import kuehlfrank.backend.dto.RecipeSuggestion;
import kuehlfrank.backend.dto.SuggestionRecipeIngredient;
import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.model.RecipeIngredient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class RecipeJpaRepositoryImpl implements RecipeJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public static final String bestMatchesQueryJPQL = "SELECT new kuehlfrank.backend.dto.RecipeSuggestion(" +
			"r.recipeId, r.name, r.external_img_src_url, r.external_source, " +
			"(0.5 * ((count(r.recipeId) - count(ie.ingredient.ingredientId) - sum(case when i.common = true then 1 else 0 end)) + abs(count(r.recipeId) - count(ie.ingredient.ingredientId) - sum(case when i.common = true then 1 else 0 end)))) AS missingIngredientCount" +
			") FROM Recipe r " +
			"left join RecipeIngredient ri on ri.recipeIngredientId.recipeId = r.recipeId " +
			"inner join User u on u.userId = :userId " +
			"left join InventoryEntry ie on ie.inventory.inventoryId = u.inventory.inventoryId and ri.ingredient.ingredientId = ie.ingredient.ingredientId " +
			"inner join Ingredient i on i.ingredientId = ri.ingredient.ingredientId " +
			"group by r.recipeId, r.name, r.external_img_src_url, r.external_source " +
			"order by missingIngredientCount, r.recipeId";

	@Override
	public Collection<RecipeSuggestion> findBestMatchingRecipes(String userId, Pageable pageable) {
		TypedQuery<RecipeSuggestion> query = entityManager.createQuery(bestMatchesQueryJPQL, RecipeSuggestion.class).setParameter("userId", userId)
				.setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize());

		return query.getResultList();
	}


	private static final String detailedRecipeQueryJPQL = "SELECT new kuehlfrank.backend.dto.RecipeIngredientStatus(" +
			"r.recipeId, ri.ingredient.ingredientId, case when ie.ingredient.ingredientId is null then false else true end, i.common) FROM Recipe r " +
			"left join RecipeIngredient ri on ri.recipeIngredientId.recipeId = r.recipeId " +
			"inner join User u on u.userId = :userId " +
			"left join InventoryEntry ie on ie.inventory.inventoryId = u.inventory.inventoryId and ri.ingredient.ingredientId = ie.ingredient.ingredientId " +
			"inner join Ingredient i on i.ingredientId = ri.ingredient.ingredientId " +
			"where r.recipeId = :recipeId " +
			"order by ri.ingredient.ingredientId";


	@Override
	public DetailedRecipeSuggestion getDetailedRecipeSuggestion(UUID recipeId, String userId) {
		TypedQuery<RecipeIngredientStatus> ingredientStatusQuery = entityManager.createQuery(detailedRecipeQueryJPQL, RecipeIngredientStatus.class).setParameter("recipeId", recipeId).setParameter("userId", userId);
		TypedQuery<Recipe> recipeQuery = entityManager.createQuery("SELECT r FROM Recipe r WHERE r.recipeId = :recipeId", Recipe.class).setParameter("recipeId", recipeId);

		Recipe recipe = recipeQuery.getSingleResult();
		List<RecipeIngredientStatus> ingredientStatuses = ingredientStatusQuery.getResultList();

		List<SuggestionRecipeIngredient> recipeIngredients = new ArrayList<>();

		int missingIngredientsCount = 0;
		for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
			RecipeIngredientStatus recipeIngredientStatus = ingredientStatuses.stream().filter(s -> s.getIngredientId().equals(ri.getRecipeIngredientId().getIngredientId())).findFirst().orElseThrow(() -> new RuntimeException("Could not find ingredient status"));

			if(recipeIngredientStatus.isMissing()) {
				missingIngredientsCount++;
			}

			recipeIngredients.add(new SuggestionRecipeIngredient(recipeIngredientStatus.isMissing(), ri.getIngredient(), ri.getAmount(), ri.getUnit()));
		}

		return new DetailedRecipeSuggestion(recipe.getRecipeId(), recipe.getName(), recipe.getExternal_img_src_url(), recipe.getExternal_source(), missingIngredientsCount, recipeIngredients);
	}
}
