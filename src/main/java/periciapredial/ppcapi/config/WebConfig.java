package periciapredial.ppcapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**") // Especifica quais endpoints permitir
        .allowedOrigins("http://localhost:4200") // Define a origem permitida
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
        .allowedHeaders("*") // Headers permitidos
        .allowCredentials(true); // Permite credenciais
  }
}
