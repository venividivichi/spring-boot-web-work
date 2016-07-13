package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpringBootWebApplication {

    public static void main(String[] args)  throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
