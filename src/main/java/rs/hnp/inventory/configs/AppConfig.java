package rs.hnp.inventory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rs.hnp.inventory.mappers.ArticleDTOMapper;

import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper articleModelMapper() {
    return new ArticleDTOMapper();
  }

}
