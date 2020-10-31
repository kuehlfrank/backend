package kuehlfrank.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class UpdateInventoryEntryDto {

    private BigDecimal amount;
    private UUID unitId;
    private String imgSrc;

}
