package kuehlfrank.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSuggestion {
    private UUID recipeId;
    private String title;
    private String imgSrc;
    private String externalSource;
    private Integer missingIngredientsCount;

    public RecipeSuggestion(UUID recipeId, String title, String imgSrc, String externalSource, double missingIngredientsCount) {
        this.recipeId = recipeId;
        this.title = title;
        this.imgSrc = imgSrc;
        this.externalSource = externalSource;
        this.missingIngredientsCount = (int)missingIngredientsCount;
    }
}
