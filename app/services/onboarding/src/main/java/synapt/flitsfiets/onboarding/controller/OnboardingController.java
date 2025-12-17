package synapt.flitsfiets.onboarding.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.enums.SubscriptionType;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    @PostMapping
//    @GetMapping
    public UserExtendedDTO onboardUser() {

        UserExtendedDTO newUser = new UserExtendedDTO();
        newUser.setId(123L);
        newUser.setName("Eik");
        newUser.setActiveSubscription(SubscriptionType.ELECTRIC);

        return newUser;
    }
}
