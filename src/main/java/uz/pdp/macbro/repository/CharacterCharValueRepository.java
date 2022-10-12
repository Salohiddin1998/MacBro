package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.CharacterCharValues;

public interface CharacterCharValueRepository extends JpaRepository<CharacterCharValues, Integer> {


    @Query(nativeQuery = true, value = "select id from characters_ch_values where character_id=:characterId and" +
            " character_value_id=:characterValueId")
    Integer findElementFromDb(Integer characterId, Integer characterValueId);

}
