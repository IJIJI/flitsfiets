package synapt.flitsfiets.onboarding.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.common.dto.user.creation.UserCreationDTO;
import synapt.flitsfiets.common.enums.AppointmentType;
import synapt.flitsfiets.common.enums.Location;
import synapt.flitsfiets.common.enums.UserType;
import synapt.flitsfiets.common.valueObject.UserAddress;
import synapt.flitsfiets.onboarding.service.AppointmentService;
import synapt.flitsfiets.onboarding.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final UserService userService;
    private final AppointmentService appointmentService;

    public OnboardingController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> unauthorized() {
        return new ResponseEntity<>("A user already exists by that email address!", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> onboardUser(@RequestBody UserCreationDTO requestedUser) {

        UserFullPasswordDTO fullUser = new UserFullPasswordDTO();
        fullUser.setName(requestedUser.getPersonal().getName());
        fullUser.setSurname(requestedUser.getPersonal().getSurname());
        fullUser.setEmail(requestedUser.getContact().getEmail());
        fullUser.setTelephone(requestedUser.getContact().getTelephone());
        fullUser.setType(UserType.USER);
        fullUser.setAddress(new UserAddress(
                requestedUser.getAddress().getStreet(),
                requestedUser.getAddress().getStreetNumber(),
                requestedUser.getAddress().getCity(),
                requestedUser.getAddress().getPostalCode(),
                requestedUser.getAddress().getCountry()
        ));
        fullUser.setPassword(requestedUser.getContact().getPassword());
        fullUser.setActiveBikeType(requestedUser.getBikeType());
        fullUser.setActivePlanType(requestedUser.getPlanType());

//        System.out.println("User Requested:");
//        System.out.println(requestedUser);
//        System.out.println("User Created (PICKUP CITY):");
//        System.out.println(requestedUser.getPickupCity());

        UserExtendedDTO newUser = userService.onBoardUser(fullUser);

        List<TimeSlotDTO> slots = null;

        try {

            slots = appointmentService.getAvailability(requestedUser.getPickupCity());
            if (slots == null || slots.isEmpty())
                throw new Exception("Failed loading slots");
        }
        catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("error", "appointments");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        Map<String, Object> result = new HashMap<>();

        result.put("user", newUser);
//        result.put("slots", slots);
        result.put("slots", slots);

//        System.out.println("User Created:");
//        System.out.println(newUser);
//        System.out.println("Slots Length:");
//        System.out.println(slots.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/apTest")
    public List<TimeSlotDTO> getAppointmentTest(@RequestParam(value = "loc") Location location ) {

        return appointmentService.getAvailability(location);

    }



    @PostMapping("/appointment")
    public AppointmentDTO onboardUser(@RequestBody AppointmentDTO appointment) {
        System.out.println("Appointment Requested: " + appointment);

        appointment.setType(AppointmentType.PICKUP); // There should be more validation...

        return appointmentService.requestAppointment(appointment);
    }
}
