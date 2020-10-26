package kuehlfrank.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long>{

	
	@Query("SELECT inv FROM Inventory inv INNER JOIN User u ON inv.inventoryId = u.inventory.inventoryId WHERE u.userId = :userId")
	Optional<Inventory> findByUserId(String userId);

	@Query("SELECT invEntry.ingredient from InventoryEntry invEntry where invEntry.inventoryId = :inventoryEntryId")
	Optional<Ingredient> findByInventoryEntryId(Long inventoryEntryId);

}
