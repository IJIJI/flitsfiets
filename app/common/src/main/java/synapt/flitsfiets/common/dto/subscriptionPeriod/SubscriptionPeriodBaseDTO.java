package synapt.flitsfiets.common.dto.subscriptionPeriod;

import synapt.flitsfiets.common.dto.user.UserBaseDTO;
import synapt.flitsfiets.common.enums.SubscriptionType;

import java.time.Instant;

public class SubscriptionPeriodBaseDTO {

    private SubscriptionType subscription;

    private Instant startDate;
    private Instant endDate;
}
