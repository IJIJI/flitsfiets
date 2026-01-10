package synapt.flitsfiets.common.config.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.config.ServicesProperties;

import java.util.Objects;

@Configuration
public class AppointmentsRestConfig
{
    @Bean
    public RestClient appointmentsRestClient(ServicesProperties servicesProperties)
    {
        var appointments = Objects.requireNonNull(servicesProperties.appointments(), "Missing config: services.appointments");
        var baseUrl = Objects.requireNonNull(appointments.baseUrl(), "Missing config: services.appointments.base-url");

        RestClient.Builder builder = RestClient.builder();

        return builder
                .baseUrl(baseUrl)
                .build();
    }
}

