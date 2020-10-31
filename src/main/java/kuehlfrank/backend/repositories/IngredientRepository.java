package kuehlfrank.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID>{

    @Query("SELECT ing FROM Ingredient ing  WHERE ing.name = :ingredientName")
    Optional<Ingredient> findByName(String ingredientName);

    @Query("SELECT ing.name FROM Ingredient ing ORDER BY ing.name")
	public Collection<String> getNames();
}
