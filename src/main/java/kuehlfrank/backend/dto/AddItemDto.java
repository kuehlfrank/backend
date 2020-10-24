package kuehlfrank.backend.dto;

import java.math.BigDecimal;

public class AddItemDto {

    private Long ingredientId;

    private BigDecimal amount;

    private Long unitId;

    public Long getIngredientId() {
        return ingredientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getUnitId() {
        return unitId;
    }
}
