package synapt.flitsfiets.onboarding.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.common.dto.user.creation.UserCreationDTO;
import synapt.flitsfiets.common.enums.UserType;
import synapt.flitsfiets.common.valueObject.UserAddress;
import synapt.flitsfiets.onboarding.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final UserService userService;

    public OnboardingController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> unauthorized() {
        return new ResponseEntity<>("A user already exists by that email address!", HttpStatus.CONFLICT);
    }

    @PostMapping
    public Map<String, Object> onboardUser(@RequestBody UserCreationDTO requestedUser) {

        UserFullPasswordDTO fullUser = new UserFullPasswordDTO();
        fullUser.setName(requestedUser.getPersonal().getName());
        fullUser.setSurname(requestedUser.getPersonal().getSurname());
        fullUser.setEmail(requestedUser.getContact().getEmail());
        fullUser.setTelephone(requestedUser.getContact().getTelephone());
        fullUser.setType(UserType.USER);
        fullUser.setAddress(new UserAddress(
                requestedUser.getAddress().getStreet(),
                requestedUser.getAddress().getStreetNumber(),
                requestedUser.getAddress().getCity(),
                requestedUser.getAddress().getPostalCode(),
                requestedUser.getAddress().getCountry()
        ));
        fullUser.setPassword(requestedUser.getContact().getPassword());
        fullUser.setActiveBikeType(requestedUser.getBikeType());
        fullUser.setActivePlanType(requestedUser.getPlanType());

        UserExtendedDTO newUser = userService.onBoardUser(fullUser);

        System.out.println("User Created:");
        System.out.println(newUser);

        Map<String, Object> result = new HashMap<>();

        result.put("user", newUser);

        return result;
    }
}
