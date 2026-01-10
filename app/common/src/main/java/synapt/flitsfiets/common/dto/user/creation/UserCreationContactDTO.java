package synapt.flitsfiets.common.dto.user.creation;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreationContactDTO
{
    String telephone;
    String email;
    String password;
}
