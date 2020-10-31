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
	private Integer totalIngredientsCount;

	public RecipeSuggestion(UUID recipeId, String title, String imgSrc, String externalSource,
			Long missingIngredientsCount, Long totalIngredientsCount) {
		this(recipeId, title, imgSrc, externalSource, missingIngredientsCount.intValue(), totalIngredientsCount.intValue());
	}
}
