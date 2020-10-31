package kuehlfrank.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionRecipeIngredient {

	private boolean missing;
	@JsonIgnoreProperties(ignoreUnknown = true, value = { "createdAt", "updatedAt" })
	private Ingredient ingredient;
	private BigDecimal amount;
	@JsonIgnoreProperties(ignoreUnknown = true, value = { "createdAt", "updatedAt" })
	private Unit unit;
}
