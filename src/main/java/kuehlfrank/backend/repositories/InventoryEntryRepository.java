package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.InventoryEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryEntryRepository extends CrudRepository<InventoryEntry, Long>{

}
