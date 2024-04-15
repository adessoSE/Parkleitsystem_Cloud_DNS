package de.adesso.clouddns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DnsConfiguration {

    @Bean
    public Map<String, String> dns(){
        return new HashMap<>();
    }

}
