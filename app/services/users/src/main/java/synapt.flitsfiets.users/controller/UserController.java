package synapt.flitsfiets.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> unauthorized() {
        return new ResponseEntity<>("A user already exists by that email address!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public UserExtendedDTO createUser(@RequestBody UserFullPasswordDTO requestedUser) {

        if(userService.emailExists(requestedUser.getEmail())){
            throw new DuplicateKeyException("A user already exists by that email address!");
        }

        UserExtendedDTO newUser = userService.createUser(requestedUser);

        System.out.println("User Created in USER SERVICE:");
        System.out.println(newUser);
        return newUser;
    }

}
