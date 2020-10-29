package kuehlfrank.backend.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class EnsureUserRegisteredDto {
	@NonNull private String name;
}
