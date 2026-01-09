package synapt.flitsfiets.appointments.messaging.appointments;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.appointments.AppointmentCreated;

import java.time.Instant;
import java.util.UUID;

@Component
public class AppointmentEventPublisher
{

    private final RabbitTemplate rabbitTemplate;

    public AppointmentEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAppointmentCreated(AppointmentCreated event) {
        var envelope = new EventEnvelope<>(
                UUID.randomUUID(),
                Instant.now(),
                AppointmentMessagingConfig.RK_APPOINTMENT_CREATED,
                event
        );

        rabbitTemplate.convertAndSend(
                AppointmentMessagingConfig.APPOINTMENTS_EVENTS_EXCHANGE,
                AppointmentMessagingConfig.RK_APPOINTMENT_CREATED,
                envelope
        );
    }
}
