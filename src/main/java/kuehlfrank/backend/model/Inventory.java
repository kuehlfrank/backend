package kuehlfrank.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_id_generator")
	@SequenceGenerator(name = "inventory_id_generator", sequenceName = "inventory_inventory_id_seq", allocationSize = 1)
	private Long inventoryId;

	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "inventory_id")
	@OneToMany
	private List<InventoryEntry> inventoryEntries;

}
