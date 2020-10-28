package kuehlfrank.backend.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
public class UpdateInventoryEntryDto {

    @NonNull private BigDecimal quantity;
    @NonNull private Integer unitId;
    private String imgSrc;

}
