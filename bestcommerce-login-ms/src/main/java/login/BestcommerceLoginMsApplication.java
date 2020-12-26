package login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class BestcommerceLoginMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestcommerceLoginMsApplication.class, args);
	}

}
