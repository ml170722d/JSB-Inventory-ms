package rs.hnp.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import rs.hnp.inventory.configs.AbstractTestcontainers;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.test.yaml")
class InventoryApplicationTests extends AbstractTestcontainers {

	@Test
	void contextLoads() {
	}

}
