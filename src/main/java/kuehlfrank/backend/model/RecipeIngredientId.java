package kuehlfrank.backend.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeIngredientId implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "recipe_id")
	@JsonIgnore
	private UUID recipeId;
	@Column(name = "ingredient_id")
	@JsonIgnore
	private UUID ingredientId;
	
}
