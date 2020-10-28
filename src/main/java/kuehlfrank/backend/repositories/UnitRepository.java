package kuehlfrank.backend.repositories;

import kuehlfrank.backend.model.Unit;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface UnitRepository extends CrudRepository<Unit, UUID>{

}
