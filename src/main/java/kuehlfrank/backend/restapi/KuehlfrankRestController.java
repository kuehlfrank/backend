package kuehlfrank.backend.restapi;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.repositories.IngredientRepository;
import kuehlfrank.backend.repositories.InventoryRepository;
import kuehlfrank.backend.repositories.RecipeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Log4j2
public class KuehlfrankRestController {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping("/inventory")
	public Inventory getInventory(Authentication authentication, @RequestParam(value = "userId", required = false) String userId) {

		if(userId == null) {
			userId = authentication.getName(); // get users own inventory if not specified otherwise
		}
		else if(!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory for now
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other users inventory");
		}

		return inventoryRepository.findByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));
	}

	@PutMapping("/inventory")
	public void setInventory(@RequestBody Inventory inventory) {
		// TODO
		log.info(inventory);
	}

	@PostMapping("/ingredient") // TODO rmv
	public Ingredient setIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	@GetMapping(value = "/recipes")
	public Collection<Recipe> recipes(@RequestParam Long userId) { //TODO user auth
		return recipeRepository.findPossibleRecipes(userId);
	}
}
