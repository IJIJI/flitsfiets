package synapt.flitsfiets.onboarding.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.enums.Location;

import java.nio.charset.StandardCharsets;
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
}
