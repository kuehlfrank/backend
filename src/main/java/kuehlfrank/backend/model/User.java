package kuehlfrank.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "KF_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "kf_user_user_id_seq", allocationSize = 1)
	private Long userId;
	private String email;
	private String name;
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
}
