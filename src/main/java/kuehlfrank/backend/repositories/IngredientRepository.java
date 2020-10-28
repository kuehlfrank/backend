package kuehlfrank.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer>{

    @Query("SELECT ing FROM Ingredient ing  WHERE ing.name = :ingredientName")
    Optional<Ingredient> findByName(String ingredientName);
}
