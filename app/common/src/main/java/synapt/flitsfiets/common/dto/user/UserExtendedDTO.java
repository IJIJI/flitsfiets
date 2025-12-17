package synapt.flitsfiets.common.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import synapt.flitsfiets.common.enums.SubscriptionType;
import synapt.flitsfiets.common.enums.UserType;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserExtendedDTO extends UserBaseDTO{

    private SubscriptionType activeSubscription;
}
