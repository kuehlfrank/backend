package kuehlfrank.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}
