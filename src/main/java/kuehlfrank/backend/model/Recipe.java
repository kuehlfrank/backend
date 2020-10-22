package kuehlfrank.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_entry_id_generator")
	@SequenceGenerator(name = "recipe_entry_id_generator", sequenceName = "recipe_entry_recipe_entry_id_seq", allocationSize = 1)
	private Long recipeId;
	@OneToMany
	@JoinColumn(name = "recipe_id")
	private List<RecipeIngredient> recipeingredients;
}
