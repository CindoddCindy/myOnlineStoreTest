package mytokoonlinetest.myonliestoretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyonliestoretestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyonliestoretestApplication.class, args);
	}

}
