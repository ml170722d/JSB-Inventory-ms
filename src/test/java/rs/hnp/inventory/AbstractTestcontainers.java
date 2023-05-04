package rs.hnp.inventory;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;

@Testcontainers
public abstract class AbstractTestcontainers {

  @Container
  protected static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.2")
      .withDatabaseName("inventory");

  @DynamicPropertySource
  public static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
  }

  private static DataSource getDataSource() {
    return DataSourceBuilder.create()
        .driverClassName(postgreSQLContainer.getDriverClassName())
        .url(postgreSQLContainer.getJdbcUrl())
        .username(postgreSQLContainer.getUsername())
        .password(postgreSQLContainer.getPassword())
        .build();
  }

  protected static JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }

}
