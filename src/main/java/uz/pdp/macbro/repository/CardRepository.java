package uz.pdp.macbro.repository;

import antlr.collections.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.Card;

import java.math.BigInteger;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {


    boolean existsByCardNumber(BigInteger cardNumber);



}
