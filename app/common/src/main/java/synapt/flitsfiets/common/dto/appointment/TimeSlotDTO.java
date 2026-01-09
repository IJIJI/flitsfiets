package synapt.flitsfiets.common.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import synapt.flitsfiets.common.enums.Location;

import java.time.Instant;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTO
{
    long id;
    Instant start;
    Instant end;
    Location location;
    Integer spots;
    Integer spotsFilled;
}
