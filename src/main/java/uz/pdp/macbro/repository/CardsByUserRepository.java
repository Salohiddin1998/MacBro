package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.Card;
import uz.pdp.macbro.projection.UserCardProjection;

import java.util.List;

public interface CardsByUserRepository extends JpaRepository<Card, Integer> {

    @Query(nativeQuery = true, value = "select c.bank_name as bankName,\n" +
            "       c.card_number as cardNumber,\n" +
            "       c.balance as balance\n" +
            "from card c join users u on u.id = c.user_id where u.id= :userId")
    List<UserCardProjection> getAllCards(Integer userId);
}
