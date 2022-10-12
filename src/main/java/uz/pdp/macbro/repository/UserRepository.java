package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM users where phone_number=:phoneNumber and id !=:id")
    Integer existsByPhoneNumberAndIdNotEquals(String phoneNumber, Integer id);

}