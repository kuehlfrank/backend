package kuehlfrank.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

    Optional<Ingredient> findByName(String ingredientName);
}
