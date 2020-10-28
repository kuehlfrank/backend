package kuehlfrank.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.UUID;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IngredientAlternativeName {
	@Id
	@GeneratedValue
	private UUID ingredientAlternativeNameId;

	private String name;

	@ManyToOne
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	public IngredientAlternativeName(String name) {
		this.name = name;
	}

	public IngredientAlternativeName(String name, Ingredient ingredient) {
		this.name = name;
		this.ingredient = ingredient;
	}
}
