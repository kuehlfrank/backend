package kuehlfrank.backend.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class DetailedRecipeSuggestion extends RecipeSuggestion {

    private List<SuggestionRecipeIngredient> recipeIngredients;

    public DetailedRecipeSuggestion(UUID recipeId, String title, String imgSrc, String externalSource, Long missingIngredientsCount, Long totalIngredientsCount, List<SuggestionRecipeIngredient> recipeIngredients) {
        super(recipeId, title, imgSrc, externalSource, missingIngredientsCount, totalIngredientsCount);
        this.recipeIngredients = recipeIngredients;
    }
}
