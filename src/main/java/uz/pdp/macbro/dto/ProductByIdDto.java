package uz.pdp.macbro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductByIdDto {

    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;

    private Integer mainImageId;

    private Integer categoryId;

    private List<Integer> imagesIds;
}
