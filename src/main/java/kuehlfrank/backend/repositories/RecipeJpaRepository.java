package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.RecipeResponse;

import java.util.Collection;

public interface RecipeJpaRepository {

	Collection<RecipeResponse> findBestMatchingRecipes(String userId);
}
