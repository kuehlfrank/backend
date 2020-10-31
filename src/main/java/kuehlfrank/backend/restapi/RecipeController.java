package kuehlfrank.backend.restapi;

import kuehlfrank.backend.model.Recipe;
import kuehlfrank.backend.model.RecipeResponse;
import kuehlfrank.backend.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(path = "recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping(value = "/suggestions/{userId}")
	public Collection<RecipeResponse> getRecipes(Authentication authentication, @PathVariable String userId) {
		if (!Objects.equals(userId, authentication.getName())) { // Only allow getting users own data for now
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other user's data");
		}
		return recipeRepository.findBestMatchingRecipes(userId);
	}

	@GetMapping(value = "/suggestions")
	public Collection<RecipeResponse> recipes(Authentication authentication) {
		return getRecipes(authentication, authentication.getName());
	}
	
	@GetMapping(value = "/random")
	public Recipe getRandomRecipe(Authentication authentication) {
		return recipeRepository.getRandomRecipe();
	}

}
