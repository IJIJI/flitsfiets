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
public class TimeSlotFormattedDTO
{
    long id;
    String start;
    String end;
    String title;
    Integer spots;
    Integer spotsFilled;
    String backgroundColor; // Client side?
    Boolean full;
}
