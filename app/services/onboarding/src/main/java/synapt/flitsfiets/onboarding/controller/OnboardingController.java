package synapt.flitsfiets.onboarding.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.creation.UserCreationDTO;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    @PostMapping
//    @GetMapping
    public Map<String, Object> onboardUser(@RequestBody UserCreationDTO requestedUser) {

        System.out.println("User Requested:");
        System.out.println(requestedUser);

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
