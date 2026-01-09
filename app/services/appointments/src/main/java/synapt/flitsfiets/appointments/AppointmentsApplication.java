package synapt.flitsfiets.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import synapt.flitsfiets.common.config.MessagingConfig;
import synapt.flitsfiets.common.config.ModelMapperConfig;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "synapt.flitsfiets.common")
@Import({ModelMapperConfig.class, MessagingConfig.class})
public class AppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}

}
