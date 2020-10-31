package kuehlfrank.backend.restapi;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.dto.DetailedRecipeSuggestion;
import kuehlfrank.backend.dto.RecipeSuggestion;
import kuehlfrank.backend.repositories.RecipeRepository;

@RestController
@RequestMapping(path = "recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping(value = "/suggestions")
	public Collection<RecipeSuggestion> getRecipes(Authentication authentication) {
		return recipeRepository.findBestMatchingRecipes(authentication.getName(), PageRequest.of(0, 10));
	}

	@GetMapping(value = "/random")
	public DetailedRecipeSuggestion getRandomRecipe(Authentication authentication) {
		UUID recipeID = UUID.fromString(recipeRepository.getRandomRecipeId());
		String userId = authentication.getName();
		return recipeRepository.getDetailedRecipeSuggestion(recipeID, userId);
	}

	@GetMapping(value = "/suggestions/suggestion/{recipeId}")
	public DetailedRecipeSuggestion getDetailedRecipeSuggestion(Authentication authentication,
			@PathVariable String recipeId) {
		return recipeRepository.getDetailedRecipeSuggestion(UUID.fromString(recipeId), authentication.getName());
	}

}
