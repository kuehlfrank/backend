package kuehlfrank.backend.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterUserDto {
	@NonNull private String userId;
	@NonNull private String name;
	@NonNull private String webhookSecret;
}
