package upc.edu.pe.vitameet_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "upc.edu.pe.vitameet_backend")
public class VitameetBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(VitameetBackendApplication.class, args);
    }
}
