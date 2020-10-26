package kuehlfrank.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.InventoryEntry;

public interface InventoryEntryRepository extends CrudRepository<InventoryEntry, Long> {

	@Query("SELECT inve FROM InventoryEntry inve WHERE inve.ingredient.ingredientId = :ingredientId AND inve.unit.unitId = :unitId")
	Optional<InventoryEntry> findByIngredientAndUnitId(Long ingredientId, Long unitId);

}
