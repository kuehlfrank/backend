package kuehlfrank.backend.model;

import lombok.Data;

@Data
public class User {
	private long id;
	private String email;
	private String name;
}
