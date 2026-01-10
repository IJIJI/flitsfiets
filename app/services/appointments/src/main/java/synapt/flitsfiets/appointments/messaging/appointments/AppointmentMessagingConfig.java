package synapt.flitsfiets.appointments.messaging.appointments;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentMessagingConfig
{

    public static final String APPOINTMENTS_EVENTS_EXCHANGE = "ff.appointments.events";
    public static final String RK_APPOINTMENT_CREATED = "users.appointment.created.v1";

    @Bean
    public TopicExchange appointmentsEventsExchange() {
        return ExchangeBuilder.topicExchange(APPOINTMENTS_EVENTS_EXCHANGE).durable(true).build();
    }
}
