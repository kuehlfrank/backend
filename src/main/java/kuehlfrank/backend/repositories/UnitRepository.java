package kuehlfrank.backend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Unit;

public interface UnitRepository extends CrudRepository<Unit, UUID>{

	@Query("SELECT u from Unit u ORDER BY LOWER(u.label)")
    public List<Unit> findAllByOrderByLabelAsc();

}
