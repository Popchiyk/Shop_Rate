package WebDiplom.InfoPage;

import WebDiplom.InfoPage.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
@Import({SwaggerConfig.class})
public class InfoPage {

    public static void main(String[] args) {
        SpringApplication.run(InfoPage.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**").allowedOrigins("http://localhost:4200/")
                        .allowedMethods("GET", "POST,", "PUT", "DELETE")
                        .maxAge(3600)
                        .allowedHeaders("Requestor-Type")
                        .exposedHeaders("X-Get-Header");
            }
        };
    }
}
