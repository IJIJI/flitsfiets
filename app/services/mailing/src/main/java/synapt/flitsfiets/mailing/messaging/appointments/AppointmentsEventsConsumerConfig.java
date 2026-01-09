package synapt.flitsfiets.mailing.messaging.appointments;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentsEventsConsumerConfig
{
    public static final String APPOINTMENTS_EVENTS_EXCHANGE = "ff.appointments.events";
    public static final String RK_APPOINTMENT_CREATED = "users.appointment.created.v1";
    //    Mailing owned queue for appointment created events
    public static final String Q_APPOINTMENT_CREATED = "ff.mailing.q.appointments.created";




    @Bean
    public TopicExchange appointmentsEventsExchange()
    {
        return ExchangeBuilder.topicExchange(APPOINTMENTS_EVENTS_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue mailingAppointmentsCreatedQueue()
    {
        return QueueBuilder.durable(Q_APPOINTMENT_CREATED).build();
    }

    @Bean
    public Binding bindAppointmentsCreated(Queue mailingAppointmentsCreatedQueue, TopicExchange appointmentsEventsExchange)
    {
        return BindingBuilder
                .bind(mailingAppointmentsCreatedQueue)
                .to(appointmentsEventsExchange)
                .with(RK_APPOINTMENT_CREATED);
    }
}
