package uz.pdp.macbro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.macbro.dto.UserDto;
import uz.pdp.macbro.entity.Card;
import uz.pdp.macbro.entity.User;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.repository.CardRepository;
import uz.pdp.macbro.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    public List<User> all() {
        return userRepository.findAll();
    }


    public User getId(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);

    }

    public Result delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Card> cards = user.getCards();
            userRepository.deleteById(id);
            for (Card card : cards) {
                cardRepository.deleteById(card.getId());
            }
            return new Result("User deleted", true);
        }
        return new Result("User not found", false);
    }

    public Result add(UserDto userDto) {

        Result result = checkingPhoneNumber(userDto.getPhoneNumber());
        if (!result.isSuccess()) {
            return result;
        }
        String phoneNumber = (String) result.getObject();
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
        if (existsByPhoneNumber) {
            return new Result("Phone Number already exist", false);
        }
        Result checkingPassword = checkingPassword(userDto.getPassword());
        if (!checkingPassword.isSuccess()){
            return checkingPassword;
        }
        if (!userDto.getPassword().equals(userDto.getPrePassword())) {
            return new Result("PrePassword not equals password", false);
        }
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(phoneNumber);
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new Result("User added", true);
    }

    public Result update(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new Result("User not found", false);
        }
        User updateUser = optionalUser.get();
        if (userDto.getFullName() != null) {
            updateUser.setFullName(userDto.getFullName());
        }
        if (userDto.getPhoneNumber() != null) {
            Result result = checkingPhoneNumber(userDto.getPhoneNumber());
            if (!result.isSuccess()) {
                return result;
            }
            String phoneNumber = (String) result.getObject();
            Integer phoneNumberExist = userRepository.existsByPhoneNumberAndIdNotEquals(phoneNumber, id);
            if (phoneNumberExist > 0) {
                return new Result("Phone Number already exist", false);
            }
            updateUser.setPhoneNumber(phoneNumber);
        }
        if (userDto.getPassword() != null) {
            Result checkingPassword = checkingPassword(userDto.getPassword());
            if (!checkingPassword.isSuccess()){
                return checkingPassword;
            }
            if (!userDto.getPassword().equals(userDto.getPrePassword())) {
                return new Result("PrePassword not equals password", false);
            }
            updateUser.setPassword(userDto.getPassword());
        }
        userRepository.save(updateUser);
        return new Result("User updated", true);
    }

    public Result checkingPhoneNumber(String phoneNumber) {
        int length = phoneNumber.length();
        if (length == 9) {
            phoneNumber = "+998" + phoneNumber;
            return new Result(true, phoneNumber);
        }
        if (length == 12) {
            phoneNumber = "+" + phoneNumber;
            return new Result(true, phoneNumber);
        }
        if (length == 13) {
            if (phoneNumber.startsWith("+")) {
                return new Result(true, phoneNumber);
            }
        }
        return new Result("Phone number was entered incorrectly", false);
    }

    public Result checkingPassword(String password) {
        // eng kamida 1 ta Katta harf va kichik harf
        // 1 son ishlatilsin
        // 8-15 oralig'ida bo'lsin
        // bosh joy bo'lamsligi kerak
        if (password.length() < 8 || password.length() > 15) {
            return new Result("Password length error", false);
        }
        if (password.contains(" ")) {
            return new Result("There can be no spaces between letters", false);
        }
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;
        int digitCounter = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                uppercaseCounter++;
            } else if (Character.isLowerCase(c)) {
                lowercaseCounter++;
            } else if (Character.isDigit(c)) {
                digitCounter++;
            }
        }
        if (uppercaseCounter == 0) {
            return new Result("assword should contain at least one uppercase letter",false);
        }
        if (lowercaseCounter == 0){
            return new Result("Password should contain at least one lowercase letter",false);
        }
        if (digitCounter == 0){
            return new Result("Password should contain at least one digit",false);
        }
        return new Result(true);
    }

}