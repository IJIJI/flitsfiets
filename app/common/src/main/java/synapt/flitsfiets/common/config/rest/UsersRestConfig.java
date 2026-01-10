package synapt.flitsfiets.common.config.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.config.ServicesProperties;

import java.util.Objects;

@Configuration
public class UsersRestConfig
{
    @Bean
    public RestClient usersRestClient(ServicesProperties servicesProperties)
    {
        var users = Objects.requireNonNull(servicesProperties.users(), "Missing config: services.users");
        var baseUrl = Objects.requireNonNull(users.baseUrl(), "Missing config: services.users.base-url");

        RestClient.Builder builder = RestClient.builder();

        return builder
                .baseUrl(baseUrl)
                .build();
    }
}

