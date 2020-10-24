package kuehlfrank.backend.model;

import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InventoryEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_entry_id_generator")
	@SequenceGenerator(name = "inventory_entry_id_generator", sequenceName = "inventory_entry_inventory_entry_id_seq", allocationSize = 1)
	private Long inventoryEntryId;

	@JsonIgnoreProperties(ignoreUnknown = true, value = {"inventoryEntries"})
	@JoinColumn(name = "inventory_id")
	@OneToOne
	private Inventory inventory;

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
}
