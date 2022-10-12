package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.macbro.entity.CardType;

public interface CardTypeRepository extends JpaRepository<CardType,Integer> {

    boolean existsByName(String name);
}
