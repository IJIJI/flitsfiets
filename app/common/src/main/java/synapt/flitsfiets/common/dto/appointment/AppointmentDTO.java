package synapt.flitsfiets.common.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import synapt.flitsfiets.common.dto.user.UserBaseDTO;
import synapt.flitsfiets.common.enums.AppointmentType;
import synapt.flitsfiets.common.enums.Location;

import java.time.Instant;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO
{
    long id;
    Instant start;
    Instant end;
    Location location;
    UserBaseDTO user;
    AppointmentType type;
}