package synapt.flitsfiets.common.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserExtendedDTO extends UserBaseDTO{

    private BikeType activeBikeType;
    private PlanType activePlanType;

}
