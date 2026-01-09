package synapt.flitsfiets.appointments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.appointments.util.RosterUtil;
import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.enums.Location;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    @Autowired
    RosterUtil rosterUtil;

    @GetMapping
    public List<TimeSlotDTO> getPossibleSlots(@RequestParam("loc") Location location){
        return rosterUtil.generatePlaceholderRoster(location);
    }

    @PostMapping
    public AppointmentDTO requestAppointment(@RequestBody AppointmentDTO request){
        return request;
    }
}
