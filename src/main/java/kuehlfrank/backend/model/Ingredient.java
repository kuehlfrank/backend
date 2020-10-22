package kuehlfrank.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_generator")
	@SequenceGenerator(name = "ingredient_id_generator", sequenceName = "ingredient_ingredient_id_seq", allocationSize = 1)
	private Long ingredientId;
	
	private String name;

	private boolean common;
}
