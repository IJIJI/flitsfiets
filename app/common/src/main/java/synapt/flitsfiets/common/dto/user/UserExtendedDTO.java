package synapt.flitsfiets.common.dto.user;

import synapt.flitsfiets.common.enums.SubscriptionType;
import synapt.flitsfiets.common.enums.UserType;

public class UserExtendedDTO extends UserBaseDTO{

    private UserType type;
    private SubscriptionType activeSubscription;
}
