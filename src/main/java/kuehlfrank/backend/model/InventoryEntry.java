package kuehlfrank.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InventoryEntry {

	@Id
	@GeneratedValue
	private UUID inventoryEntryId;

	@JsonIgnoreProperties(ignoreUnknown = true, value = { "inventoryEntries" })
	@JoinColumn(name = "inventory_id")
	@OneToOne
	private Inventory inventory;

	@JsonIgnoreProperties(ignoreUnknown = true, value = { "alternativeNames" })
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	private BigDecimal amount;

	// URL for image associated with a item added by the user
	// Most likely a static link to openfoodfacts.org
	private String imageSrcUrl;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime createdAt;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime updatedAt;

	public InventoryEntry(Inventory inventory, Ingredient ingredient, BigDecimal amount, String imageSrcUrl,
			Unit unit) {
		this.inventory = inventory;
		this.ingredient = ingredient;
		this.amount = amount;
		this.imageSrcUrl = imageSrcUrl;
		this.unit = unit;
	}

	public InventoryEntry(UUID inventoryEntryId, Inventory inventory, Ingredient ingredient,
			@NonNull BigDecimal quantity, String imgSrc, Unit unit) {
		this(inventory, ingredient, quantity, imgSrc, unit);
		this.inventoryEntryId = inventoryEntryId;
	}

	public void increaseAmount(@NonNull BigDecimal amountToAdd) {
		amount = amount.add(amountToAdd);
	}

}
