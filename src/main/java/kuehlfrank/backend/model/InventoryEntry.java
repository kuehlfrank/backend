package kuehlfrank.backend.model;

import java.math.BigDecimal;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryEntryId;

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
	
	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private Unit unit;


	public InventoryEntry(Inventory inventory, Ingredient ingredient, BigDecimal amount, Unit unit) {
		this.inventory = inventory;
		this.ingredient = ingredient;
		this.amount = amount;
		this.unit = unit;
	}

	public void increaseAmount(@NonNull BigDecimal amountToAdd) {
		amount = amount.add(amountToAdd);
	}
}
