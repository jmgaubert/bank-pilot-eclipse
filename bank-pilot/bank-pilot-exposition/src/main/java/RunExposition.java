

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.training.samples.bankpilot.application","fr.training.samples.bankpilot.domain","fr.training.samples.bankpilot.infrastructure","fr.training.samples.bankpilot.exposition" })
public class RunExposition {

	public static void main(String[] args) {
		SpringApplication.run(RunExposition.class, args);

	}

}
