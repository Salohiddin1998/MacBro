package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.macbro.entity.CharacterValue;

public interface CharacterValueRepo extends JpaRepository<CharacterValue,Integer> {
}
