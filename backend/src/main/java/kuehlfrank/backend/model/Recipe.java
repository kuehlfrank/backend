package kuehlfrank.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Recipe {

	private final long id;
	private List<Ingredient> ingredients;
}
