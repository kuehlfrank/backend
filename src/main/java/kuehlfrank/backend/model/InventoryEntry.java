package kuehlfrank.backend.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InventoryEntry {

	@Id
	@GeneratedValue
	private UUID inventoryEntryId;

	@JsonIgnoreProperties(ignoreUnknown = true, value = {"inventoryEntries"})
	@JoinColumn(name = "inventory_id")
	@OneToOne
	private Inventory inventory;

	@JsonIgnoreProperties(ignoreUnknown = true, value = {"alternativeNames"})
	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	private BigDecimal amount;

	// URL for image associated with a item added by the user
	// Most likely a static link to openfoodfacts.org
	private String imageSrcUrl;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private Unit unit;


	public InventoryEntry(Inventory inventory, Ingredient ingredient, BigDecimal amount, String imageSrcUrl, Unit unit) {
		this.inventory = inventory;
		this.ingredient = ingredient;
		this.amount = amount;
		this.imageSrcUrl = imageSrcUrl;
		this.unit = unit;
	}

	public void increaseAmount(@NonNull BigDecimal amountToAdd) {
		amount = amount.add(amountToAdd);
	}
}
