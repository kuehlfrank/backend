package kuehlfrank.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientStatus {
    private UUID recipeId;
    private UUID ingredientId;
    private boolean isInInventory;
    private boolean isCommon;

    public boolean isMissing() {
        return !(isCommon || isInInventory);
    }
}
