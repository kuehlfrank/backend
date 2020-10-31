package kuehlfrank.backend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Unit;

public interface UnitRepository extends CrudRepository<Unit, UUID>{

    public List<Unit> findAllByOrderByLabelAsc();

}
