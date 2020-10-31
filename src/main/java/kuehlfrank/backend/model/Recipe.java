package kuehlfrank.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Recipe {
	@Id
	@GeneratedValue
	private UUID recipeId;

	private String external_id;
	private String external_source;
	private String external_url;
	private String external_img_src_url;
	private String name;

	@OneToMany
	@JoinColumn(name = "recipe_id")
	private List<RecipeIngredient> recipeIngredients;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime createdAt;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime updatedAt;
}
