package ru.Kuzevanov_Alexander.NauJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the NauJava project. This class uses Spring Boot annotations to
 * configure and run the application.  The {@link EnableScheduling} annotation enables support
 * for scheduled tasks within the application.
 */
@SpringBootApplication
@EnableScheduling
public class NauJavaApplication {

    /**
     * Main method to start the Spring Boot application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(NauJavaApplication.class, args);
    }
}
