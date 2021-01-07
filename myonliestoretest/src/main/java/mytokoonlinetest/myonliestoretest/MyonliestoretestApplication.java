package mytokoonlinetest.myonliestoretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//di tambahin krna link yg lain d blokir jwt
@ComponentScan
public class MyonliestoretestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyonliestoretestApplication.class, args);
	}

}
