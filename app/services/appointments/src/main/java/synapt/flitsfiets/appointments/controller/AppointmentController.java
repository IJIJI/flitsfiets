package synapt.flitsfiets.appointments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import synapt.flitsfiets.appointments.dto.TimeslotDto;
import synapt.flitsfiets.common.enums.Location;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    @GetMapping
    public List<TimeslotDto> getPossibleSlots(@RequestParam("loc") Location location){
        return null;
    }
}
