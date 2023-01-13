package itcen.backapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class BackapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackapiApplication.class, args);
	}

}
