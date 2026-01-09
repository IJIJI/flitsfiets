package synapt.flitsfiets.common.events.appointments;

import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;

public record AppointmentCreated (
        AppointmentDTO appointment
) {}
