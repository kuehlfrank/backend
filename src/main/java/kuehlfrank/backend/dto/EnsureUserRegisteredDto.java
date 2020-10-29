package kuehlfrank.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class EnsureUserRegisteredDto {
	@NonNull private String username;
}
