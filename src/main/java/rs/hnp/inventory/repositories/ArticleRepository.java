package rs.hnp.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.hnp.inventory.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
  @Query("SELECT a FROM Article a WHERE a.amount > 0")
  List<Article> findAllAvailable();

  List<Article> findByName(String name);

  List<Article> findByExternalId(String externalId);
}
