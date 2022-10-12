package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.Character;
import uz.pdp.macbro.projection.CharacteristicProjection;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Character, Integer> {


    @Query(nativeQuery = true, value = "select c.character_name as characterName,\n" +
            "       cv.value as value\n" +
            "from character c\n" +
            "         join characters_ch_values ccv on c.id = ccv.character_id\n" +
            "         join character_value cv on cv.id = ccv.character_value_id\n" +
            "         inner join products_character_char_values pccv on ccv.id = pccv.character_char_values_id\n" +
            "         join products p on p.id = pccv.products_id where p.id= :productId")
    List<CharacteristicProjection> getCharacteristicNameAndValueByProductId(Integer productId);
}
