package kuehlfrank.backend.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateInventoryEntryDto {

    @NonNull private BigDecimal quantity;
    @NonNull private Long unitId;

}
