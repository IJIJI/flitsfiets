package synapt.flitsfiets.common.dto.subscriptionPeriod;

import lombok.Data;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

import java.time.Instant;

@Data
public class SubscriptionPeriodBaseDTO {

    private BikeType bike;
    private PlanType plan;

    private Instant startDate;
    private Instant endDate;
}
