package kuehlfrank.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID>{

    @Query("SELECT ing FROM Ingredient ing  WHERE ing.name = :ingredientName")
    Optional<Ingredient> findByName(String ingredientName);
}
