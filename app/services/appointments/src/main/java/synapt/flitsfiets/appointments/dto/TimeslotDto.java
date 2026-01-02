package synapt.flitsfiets.appointments.dto;

import synapt.flitsfiets.appointments.enums.AppointmentType;
import synapt.flitsfiets.common.enums.Location;

import java.time.Instant;

public class TimeslotDto
{
    private Long id;
    private Instant start;
    private Location location; // Shouldn't be enum
}
