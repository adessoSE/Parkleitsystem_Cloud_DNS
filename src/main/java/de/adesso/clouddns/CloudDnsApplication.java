package de.adesso.clouddns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.adesso")
public class CloudDnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDnsApplication.class, args);
    }

}
