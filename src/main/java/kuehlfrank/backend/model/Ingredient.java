package kuehlfrank.backend.model;

import lombok.Data;

@Data
public class Ingredient {
	private final long id;
	private final String name;
	private final Unit unit;
}
