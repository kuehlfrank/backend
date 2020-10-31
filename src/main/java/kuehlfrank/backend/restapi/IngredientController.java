package kuehlfrank.backend.restapi;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.repositories.IngredientRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "ingredients")
@CrossOrigin(origins = "*")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@GetMapping("")
	public Collection<String> getNames(Authentication authentication) {
		return ingredientRepository.getNames();
	}
}
