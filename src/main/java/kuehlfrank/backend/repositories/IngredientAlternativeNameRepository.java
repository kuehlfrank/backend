package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.IngredientAlternativeName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientAlternativeNameRepository extends CrudRepository<IngredientAlternativeName, Integer>{

    @Query("SELECT ing FROM IngredientAlternativeName ing WHERE ing.name = :ingredientAlternativeName")
    Optional<IngredientAlternativeName> findByName(String ingredientAlternativeName);
}
