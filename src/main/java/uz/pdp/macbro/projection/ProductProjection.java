package uz.pdp.macbro.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface ProductProjection {

    Integer getId();
    String getName();
    Integer getQuantity();
    String getPrice();
    Integer getCategoryId();

    Integer getMainImageId();

    @Value("#{@characteristicRepository.getCharacteristicNameAndValueByProductId(target.id)}")
    List<CharacteristicProjection> getCharacters();

}
