package synapt.flitsfiets.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import synapt.flitsfiets.common.config.rest.AppointmentsRestConfig;
import synapt.flitsfiets.common.config.rest.UsersRestConfig;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "synapt.flitsfiets.common")
@Import({UsersRestConfig.class, AppointmentsRestConfig.class})
public class OnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}

}
