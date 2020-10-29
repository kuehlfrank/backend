package kuehlfrank.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "KF_USER")
public class User {
	@Id
	@NonNull
	private String userId;
	@NonNull
	private String name;
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
}
