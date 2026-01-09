package synapt.flitsfiets.onboarding.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.dto.appointment.TimeSlotFormattedDTO;
import synapt.flitsfiets.common.enums.Location;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService
{
    @Autowired
    private RestClient appointmentsRestClient;

    public List<TimeSlotDTO> getAvailability(Location location)
    {
        return appointmentsRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/appointments")
                        .queryParam("loc", location)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.value() != 200,
                        (req, res) -> {
                            String body = StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8);

                            throw new BadRequestException(body);
                        }
                )
                .body(new ParameterizedTypeReference<List<TimeSlotDTO>>() {});

    }

    public List<TimeSlotFormattedDTO> getFormattedAvailability(Location location){

        List<TimeSlotDTO> slots = this.getAvailability(location);

        List<TimeSlotFormattedDTO> formatSlots = new ArrayList<>(); //TODO MODELMAPPER, PLEASE

        int index = 0; // Should be actual slot ID's - Duhh

        for ( TimeSlotDTO slot : slots){
            TimeSlotFormattedDTO formattedSlot = new TimeSlotFormattedDTO();

            formattedSlot.setId(index++);
            formattedSlot.setStart(String.valueOf(slot.getStart()));
            formattedSlot.setEnd(String.valueOf(slot.getEnd()));

            formattedSlot.setTitle(
                    ( slot.getSpots() - slot.getSpotsFilled() ) +
                    " spots left"
            );

            if(slot.getSpotsFilled().equals(slot.getSpots()))
                formattedSlot.setBackgroundColor("#C70000");
            else if ((double) slot.getSpotsFilled() /slot.getSpots() > 0.5)
                formattedSlot.setBackgroundColor("#F59B1D");
            else
            formattedSlot.setBackgroundColor("#00BA28");

            formatSlots.add(
                    formattedSlot
            );
        }

        return formatSlots;

    }
}
