package kuehlfrank.backend.restapi;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.repositories.IngredientRepository;
import kuehlfrank.backend.repositories.InventoryRepository;
import kuehlfrank.backend.repositories.RecipeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Log4j2
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;


	@PostMapping("/ingredient") // TODO rmv
	public Ingredient setIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

}
