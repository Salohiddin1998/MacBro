package uz.pdp.macbro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CharacterValuesDto {

    private Integer characterId;
    private Integer valueId;

}
