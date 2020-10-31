package kuehlfrank.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RecipeResponse {
    private UUID recipeId;
    private String title;
    private String imgSrc;
    private String externalSource;
    private Integer missingIngredientsCount;

    public RecipeResponse(UUID recipeId, String title, String imgSrc, String externalSource, double missingIngredientsCount) {
        this.recipeId = recipeId;
        this.title = title;
        this.imgSrc = imgSrc;
        this.externalSource = externalSource;
        this.missingIngredientsCount = (int)missingIngredientsCount;
    }
}
