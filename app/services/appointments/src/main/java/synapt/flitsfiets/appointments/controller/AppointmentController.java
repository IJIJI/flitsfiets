package synapt.flitsfiets.appointments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.appointments.model.Appointment;
import synapt.flitsfiets.appointments.model.UserTrack;
import synapt.flitsfiets.appointments.service.AppointmentService;
import synapt.flitsfiets.appointments.service.UserTrackService;
import synapt.flitsfiets.appointments.util.RosterUtil;
import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.enums.Location;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    @Autowired
    RosterUtil rosterUtil;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserTrackService userTrackService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TimeSlotDTO> getPossibleSlots(@RequestParam("loc") Location location){
        return rosterUtil.generatePlaceholderRoster(location);
    }

    @PostMapping
    public AppointmentDTO requestAppointment(@RequestBody AppointmentDTO request){

        Appointment newAppointment = new Appointment();
        newAppointment.setStart(request.getStart());
        newAppointment.setEnd(request.getStart().plusSeconds(rosterUtil.getSlotLength() * 60L));
        newAppointment.setType(request.getType());
        newAppointment.setLocation(request.getLocation());
        newAppointment.setType(request.getType());

        UserTrack user = userTrackService.getById(request.getUser().getId());
        newAppointment.setUser(user);

        Appointment created = appointmentService.createAppointment(newAppointment);

        return modelMapper.map(created, AppointmentDTO.class);
    }
}
