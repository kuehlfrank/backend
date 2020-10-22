package kuehlfrank.backend.dbaccess;

import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

}
