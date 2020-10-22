package kuehlfrank.backend.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RecipeIngredient {

	@EmbeddedId @JsonIgnore
	private RecipeIngredientId recipeIngredientId;

	@ManyToOne
    @MapsId("ingedientId")
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	private BigDecimal amount;
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;
}
