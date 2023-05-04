package rs.hnp.inventory.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import rs.hnp.inventory.AbstractTestcontainers;
import rs.hnp.inventory.InventoryApplication;
import rs.hnp.inventory.models.Article;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(InventoryApplication.class)
public class ArticleRepositoryTest extends AbstractTestcontainers {

  @Autowired
  private ArticleRepository unitTest;

  @BeforeEach
  void tearDown() {
    unitTest.deleteAll();
  }

  @Test
  void getArticlesByName() {
    // arrange
    String name = "test";
    Article article = new Article(
        Long.valueOf(0),
        "ABC1",
        name,
        "",
        Long.valueOf(50),
        "",
        Long.valueOf(3),
        Long.valueOf(5));

    unitTest.save(article);

    // act
    List<Article> expected = unitTest.findByName(name);

    // assert
    for (Article a : expected) {
      assertThat(a.getName()).isEqualTo(name);
    }
  }

  @Test
  void getArticlesByExternalId() {
    // arrange
    String externalId = "ABC1";
    Article article = new Article(
        Long.valueOf(0),
        externalId,
        "test",
        "",
        Long.valueOf(50),
        "",
        Long.valueOf(3),
        Long.valueOf(5));

    unitTest.save(article);

    // act
    boolean expect1 = unitTest.findByExternalId(externalId).isPresent();
    boolean expect2 = unitTest.findByExternalId(externalId + "1").isPresent();

    // assert
    assertThat(expect1).isTrue();
    assertThat(expect2).isFalse();
  }
}
