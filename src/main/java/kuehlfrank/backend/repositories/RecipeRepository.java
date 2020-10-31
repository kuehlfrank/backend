package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID>, RecipeJpaRepository {

	@Query(value = "SELECT * FROM Recipe r ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public Recipe getRandomRecipe();
}
