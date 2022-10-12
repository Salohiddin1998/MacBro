package uz.pdp.macbro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {
    private String name;
    private Double price;
    private Integer quantity;
    private Integer categoryId;
    private Integer mainImageId;

    @JsonProperty("characters")
    private List<CharacterValuesDto> characterAndValuesList;
}
