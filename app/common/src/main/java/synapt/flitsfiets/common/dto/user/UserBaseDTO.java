package synapt.flitsfiets.common.dto.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserBaseDTO {
    private Long id;

    private String name;

    private String email;
}
