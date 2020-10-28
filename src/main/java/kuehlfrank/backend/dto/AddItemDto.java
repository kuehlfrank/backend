package kuehlfrank.backend.dto;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class AddItemDto {

    // Ingredient Name
    @NonNull
    private String name;

    private Boolean common = false;

    private String[] alternative_names = new String[0];

    private String imgSrc;

    @NonNull
    private BigDecimal quantity;

    @NonNull
    private Long unitId;


    public BigDecimal getAmount() {
        return quantity;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getIngredientName() {
        return name;
    }

    public boolean isCommon() {
        return common;
    }
}
