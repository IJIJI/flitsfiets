package synapt.flitsfiets.mailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "synapt.flitsfiets.common")
public class MailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailingApplication.class, args);
	}

}
