package com.simo.hangman;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@SpringBootApplication
public class HangmanApplication {

    private static final String ACCESS_URLS_MESSAGE_LOG =
            "\n\n Access URLs:\n----------------------------------------------------------\n\t External: \thttp://localhost:{}{}/swagger-ui/ Profiles: {}\n----------------------------------------------------------\n";

    public static void main(final String[] args) {
        try {

            final SpringApplication app = new SpringApplication(HangmanApplication.class);
            final Environment env = app.run().getEnvironment();
            log.info(
                    ACCESS_URLS_MESSAGE_LOG,
                    env.getProperty("server.port"),
                    env.getProperty("server.servlet.context-path"),
                    env.getActiveProfiles());

        } catch (Exception e) {
            log.error("Start Error.", e);
        }
    }
}
