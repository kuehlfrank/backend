package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.InventoryEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryEntryRepository extends CrudRepository<InventoryEntry, Long>{

    @Query("SELECT inve FROM InventoryEntry inve WHERE inve.ingredient.ingredientId = :ingredientId AND inve.unit.unitId = :unitId")
    Optional<InventoryEntry> findByIngredientAndUnitId(Long ingredientId, Long unitId);

}
