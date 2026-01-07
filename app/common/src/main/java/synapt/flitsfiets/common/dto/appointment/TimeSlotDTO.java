package synapt.flitsfiets.common.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import synapt.flitsfiets.common.enums.Location;

import java.time.Instant;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class TimeSlotDTO
{
    Instant start;
    Instant end;
    Location location;
}
