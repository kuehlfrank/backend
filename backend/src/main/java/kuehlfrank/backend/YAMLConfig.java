package kuehlfrank.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class YAMLConfig {
 
    private String name;
    private String environment;
    private List<String> servers = new ArrayList<>();
 
 
}