package uz.pdp.macbro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.macbro.projection.UserProjection;
import uz.pdp.macbro.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepo;

    public List<UserProjection> getAllUsersFromDb() {
        List<UserProjection> allUsers = userRepo.getAllUsers();
        return allUsers;
    }

    public void deleteUserById(Integer id) {

    }
}
