package kuehlfrank.backend.repositories;

import kuehlfrank.backend.dto.DetailedRecipeSuggestion;
import kuehlfrank.backend.dto.RecipeSuggestion;

import java.util.Collection;
import java.util.UUID;

public interface RecipeJpaRepository {

	Collection<RecipeSuggestion> findBestMatchingRecipes(String userId);

	DetailedRecipeSuggestion getDetailedRecipeSuggestion(UUID recipeId, String userId);
}
