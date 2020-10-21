package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.dbaccess.IngredientRepository;
import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Inventory;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class KuehlfrankRestController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@GetMapping("/inventory")
	public String getInventory(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@PutMapping("/inventory")
	public void setInventory(@RequestBody Inventory inventory /*TODO auth?*/){
		System.out.println(inventory);
	}

	@PostMapping("/ingredient")
	public void setIngredient(@RequestBody Ingredient ingredient) {
		log.info(ingredient);
		ingredientRepository.save(ingredient);
	}

}
