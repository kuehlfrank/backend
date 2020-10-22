package kuehlfrank.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_generator")
	@SequenceGenerator(name = "unit_id_generator", sequenceName = "unit_user_id_seq", allocationSize = 1)
	private Long unitId;
	private String label;
}
