package kuehlfrank.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class AddItemDto {

    // Ingredient Name
    @NonNull
    @Getter(AccessLevel.NONE)
    private String name;

    @Getter(AccessLevel.NONE)
    private Boolean common = false;

    private String[] alternative_names = new String[0];

    private String imgSrc;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private UUID unitId;


    public String getIngredientName() {
        return name;
    }

    public boolean isCommon() {
        return common;
    }
}
