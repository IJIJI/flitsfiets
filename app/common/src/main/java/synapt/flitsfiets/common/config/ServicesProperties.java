package synapt.flitsfiets.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "services")
public record ServicesProperties (
    Service users,
    Service appointments
){
    public record Service(String baseUrl) {}
}
