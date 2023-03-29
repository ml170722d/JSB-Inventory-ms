package rs.hnp.inventory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper articleModelMapper() {
    return new ModelMapper();
  }

}
