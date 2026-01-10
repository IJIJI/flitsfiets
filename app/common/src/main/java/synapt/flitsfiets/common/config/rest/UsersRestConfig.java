package synapt.flitsfiets.common.config.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UsersRestConfig
{
    @Bean
    public RestClient usersRestClient(@Value("${services.users.base-url}") String baseUrl)
    {
        RestClient.Builder builder = RestClient.builder();

        return builder
                .baseUrl(baseUrl)
                .build();
    }
}

