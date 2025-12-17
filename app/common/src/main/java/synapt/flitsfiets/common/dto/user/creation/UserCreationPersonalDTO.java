package synapt.flitsfiets.common.dto.user.creation;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserCreationPersonalDTO {
    String name;
    String surname;
    Date birthdate;
    Integer length;
}
