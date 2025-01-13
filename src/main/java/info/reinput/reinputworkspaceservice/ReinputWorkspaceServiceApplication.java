package info.reinput.reinputworkspaceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReinputWorkspaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReinputWorkspaceServiceApplication.class, args);
    }

}
