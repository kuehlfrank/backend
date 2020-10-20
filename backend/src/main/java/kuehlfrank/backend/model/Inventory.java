package kuehlfrank.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Inventory {

	private User user;
	private List<Ingredient> ingredients;
}
