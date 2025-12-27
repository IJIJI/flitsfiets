package synapt.flitsfiets.onboarding.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.common.dto.subscriptionPeriod.SubscriptionPeriodBaseDTO;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.common.dto.user.creation.UserCreationDTO;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;
import synapt.flitsfiets.common.enums.UserType;
import synapt.flitsfiets.common.valueObject.UserAddress;
import synapt.flitsfiets.onboarding.service.UserService;

import java.time.Instant;
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

        System.out.println("User Requested:");
        System.out.println(requestedUser);


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

        userService.onBoardUser(fullUser);

        SubscriptionPeriodBaseDTO plan = new SubscriptionPeriodBaseDTO();
        plan.setPlan(requestedUser.getPlanType());
        plan.setBike(requestedUser.getBikeType());
        plan.setStartDate(Instant.now());


        Map<String, Object> result = new HashMap<>();

        UserExtendedDTO newUser = new UserExtendedDTO();
        newUser.setId(123L);
        newUser.setName("Eik");
        newUser.setEmail("eik@test.com");
        newUser.setActiveBikeType(BikeType.ELECTRIC);
        newUser.setActivePlanType(PlanType.MONTHLY);

        result.put("user", newUser);

        return result;
    }
}
