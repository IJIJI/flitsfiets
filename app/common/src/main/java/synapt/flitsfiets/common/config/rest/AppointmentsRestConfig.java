package synapt.flitsfiets.common.config.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppointmentsRestConfig
{
    @Bean
    public RestClient appointmentsRestClient(@Value("${services.appointments.base-url}") String baseUrl)
    {
        RestClient.Builder builder = RestClient.builder();

        return builder
                .baseUrl(baseUrl)
                .build();
    }
}

