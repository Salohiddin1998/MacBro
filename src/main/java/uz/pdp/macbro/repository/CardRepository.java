package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.macbro.entity.Card;

import java.math.BigInteger;

public interface CardRepository extends JpaRepository<Card,Integer> {

    boolean existsByCardNumber(BigInteger cardNumber);
}
