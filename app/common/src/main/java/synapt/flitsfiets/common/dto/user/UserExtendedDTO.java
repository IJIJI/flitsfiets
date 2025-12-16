package synapt.flitsfiets.common.dto.user;

import synapt.flitsfiets.common.enums.SubscriptionType;

public class UserExtendedDTO extends UserBaseDTO{
    private String email;
    private String telephone;

    private SubscriptionType activeSubscription;
}
