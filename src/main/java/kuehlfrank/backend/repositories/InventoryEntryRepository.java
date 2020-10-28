package kuehlfrank.backend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.InventoryEntry;

public interface InventoryEntryRepository extends CrudRepository<InventoryEntry, UUID> {

	@Query("SELECT inve FROM InventoryEntry inve WHERE inve.ingredient.ingredientId = :ingredientId AND inve.unit.unitId = :unitId")
	Optional<InventoryEntry> findByIngredientAndUnitId(UUID ingredientId, UUID unitId);

	@Query("SELECT invEntry.ingredient from InventoryEntry invEntry where invEntry.inventory.inventoryId = :inventoryEntryId")
	Optional<Ingredient> findByInventoryEntryId(UUID inventoryEntryId);
}
