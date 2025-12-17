package synapt.flitsfiets.common.dto.subscriptionPeriod;

import lombok.Data;
import synapt.flitsfiets.common.enums.SubscriptionType;

import java.time.Instant;

@Data
public class SubscriptionPeriodBaseDTO {

    private SubscriptionType subscription;

    private Instant startDate;
    private Instant endDate;
}
