package uz.pdp.macbro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.macbro.projection.UserProjection;
import uz.pdp.macbro.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserProjection> getAllusersFromDb(){
        List<UserProjection> allUsersFromDb = userService.getAllUsersFromDb();
        return allUsersFromDb;
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUserById(@PathVariable Integer id){
//        userService.deleteUserById(id);
//    }

}
