package kuehlfrank.backend.dto;

import kuehlfrank.backend.model.RecipeIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DetailedRecipeSuggestion extends RecipeSuggestion {

    private List<SuggestionRecipeIngredient> recipeIngredients;

    public DetailedRecipeSuggestion(UUID recipeId, String title, String imgSrc, String externalSource, Integer missingIngredientsCount, List<SuggestionRecipeIngredient> recipeIngredients) {
        super(recipeId, title, imgSrc, externalSource, missingIngredientsCount);
        this.recipeIngredients = recipeIngredients;
    }
}
