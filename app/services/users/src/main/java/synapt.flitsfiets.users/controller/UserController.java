package synapt.flitsfiets.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserExtendedDTO createUser(@RequestBody UserFullPasswordDTO requestedUser) {

        UserExtendedDTO newUser = userService.createUser(requestedUser);

        System.out.println("User Created in USER SERVICE:");
        System.out.println(newUser);
        return newUser;
    }

}
