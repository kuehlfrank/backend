package kuehlfrank.backend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long inventoryEntryId;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	
	private BigDecimal amount;
	
	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
}
