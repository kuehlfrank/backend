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
	private Long missingIngredientsCount;
	private Long totalIngredientsCount;
}
