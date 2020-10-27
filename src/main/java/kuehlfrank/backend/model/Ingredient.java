package kuehlfrank.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientId;
	
	private String name;

	private boolean common;

	@OneToMany
	@JoinColumn(name = "ingredient_id")
	private List<IngredientAlternativeName> alternativeNames;

	public Ingredient(String name, boolean common) {
		this.name = name;
		this.common = common;
	}

	public Ingredient(String name, boolean common, List<IngredientAlternativeName> alternativeNames) {
		this.name = name;
		this.common = common;
		this.alternativeNames = alternativeNames;
	}
}
