package kuehlfrank.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateInventoryEntryDto {

    @NonNull private BigDecimal quantity;
    @NonNull private UUID unitId;
    private String imgSrc;

}
