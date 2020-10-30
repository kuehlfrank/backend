package kuehlfrank.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {

	@Id
	@GeneratedValue
	private UUID inventoryId;

	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "inventory_id")
	@OneToMany
	private List<InventoryEntry> inventoryEntries = new ArrayList<>();

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime createdAt;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private LocalDateTime updatedAt;
}
