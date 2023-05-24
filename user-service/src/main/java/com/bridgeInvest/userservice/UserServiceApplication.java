package com.bridgeInvest.userservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
/**
 * Main class for the User Service application.
 */
@SpringBootApplication
//@EnableJpaRepositories
public class UserServiceApplication {
    /**
     * Creates and configures a WebClient bean.
     *
     * @return The configured WebClient bean.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

    @Bean
    public WebClient webclient() {
        return WebClient.builder().build();
    }
    /**
     * Main method to start the User Service application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {


        SpringApplication.run(UserServiceApplication.class, args);
        logger.info("Hello, logging!");
    }

}
