package kuehlfrank.backend.restapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.dbaccess.IngredientRepository;
import kuehlfrank.backend.dbaccess.InventoryRepository;
import kuehlfrank.backend.dbaccess.RecipeRepository;
import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Inventory;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class KuehlfrankRestController {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@GetMapping("/inventory")
	public Optional<Inventory> getInventory(@RequestParam(value = "id") long id) {
		return inventoryRepository.findById(id);
	}

	@PutMapping("/inventory")
	public void setInventory(@RequestBody Inventory inventory) {
		System.out.println(inventory);
	}

	@PostMapping("/ingredient")
	public Ingredient setIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@GetMapping("/ingredient")
	public void getRecipe() {
		log.info(recipeRepository.testFind());
	}

}
