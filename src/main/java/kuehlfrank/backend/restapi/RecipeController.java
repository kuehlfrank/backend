package kuehlfrank.backend.restapi;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.repositories.RecipeRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping(value = "/recipes/{userId}")
	public Collection<Recipe> getRecipes(Authentication authentication, @PathVariable String userId) {
		if (!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory for
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other user's data");
		}
		return recipeRepository.findPossibleRecipes(userId);
	}

	@GetMapping(value = "/recipes")
	public Collection<Recipe> recipes(Authentication authentication) {
		return getRecipes(authentication, authentication.getName());
	}
	
	@GetMapping(value = "/random")
	public Recipe getRandomRecipe(Authentication authentication) {
		return recipeRepository.getRandomRecipe();
	}

}
