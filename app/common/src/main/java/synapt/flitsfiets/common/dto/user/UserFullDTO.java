package synapt.flitsfiets.common.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import synapt.flitsfiets.common.enums.UserType;
import synapt.flitsfiets.common.valueObject.UserAddress;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFullDTO extends UserExtendedDTO{
    private String surname;
    private UserAddress address;
    private String telephone;
    private UserType type;
}
