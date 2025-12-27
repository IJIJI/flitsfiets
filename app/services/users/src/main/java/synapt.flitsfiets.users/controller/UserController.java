package synapt.flitsfiets.users.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public UserExtendedDTO createUser(@RequestBody UserFullDTO requestedUser) {
        System.out.println("User Requested in USER SERVICE:");
        System.out.println(requestedUser);
        return null;
    }

}
