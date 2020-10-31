package kuehlfrank.backend.repositories;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u.userId FROM InventoryEntry ie INNER JOIN User u ON ie.inventory.inventoryId = u.inventory.inventoryId WHERE ie.inventoryEntryId = :inventoryEntryId")
	public Set<String> getUserIdsByInventoryEntryId(UUID inventoryEntryId);
}
