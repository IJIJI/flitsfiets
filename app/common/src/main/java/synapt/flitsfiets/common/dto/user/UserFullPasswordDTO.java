package synapt.flitsfiets.common.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserFullPasswordDTO extends UserFullDTO{
    private String password;
}
