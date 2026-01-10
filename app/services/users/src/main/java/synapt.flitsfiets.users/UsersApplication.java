package synapt.flitsfiets.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import synapt.flitsfiets.common.config.MessagingConfig;
import synapt.flitsfiets.common.config.ModelMapperConfig;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = "synapt.flitsfiets.common")
@Import({ModelMapperConfig.class, MessagingConfig.class})
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
