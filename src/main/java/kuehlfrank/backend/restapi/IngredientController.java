package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.repositories.IngredientRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;


	@PostMapping("/ingredient") // TODO rmv
	public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

}
