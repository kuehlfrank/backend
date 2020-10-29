package kuehlfrank.backend.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeResponse {

	
	private Recipe recipe;
	private Integer count;

}
