package synapt.flitsfiets.mailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import synapt.flitsfiets.common.config.ModelMapperConfig;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "synapt.flitsfiets.common")
@Import({ModelMapperConfig.class})
public class MailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailingApplication.class, args);
	}

}
