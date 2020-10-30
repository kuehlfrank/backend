package kuehlfrank.backend.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ingredient {
	@Id
	@GeneratedValue
	private UUID ingredientId;
	
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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime createdAt;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	
}
