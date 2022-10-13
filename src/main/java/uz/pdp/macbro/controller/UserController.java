package uz.pdp.macbro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.macbro.dto.UserDto;
import uz.pdp.macbro.entity.User;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.service.UserService;


import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> all(){
        return userService.all();
    }

    @GetMapping("/{id}")
    public User getId(@PathVariable Integer id){
        return userService.getId(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return userService.delete(id);
    }

    @PostMapping
    public Result add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.update(id,userDto);
    }


}