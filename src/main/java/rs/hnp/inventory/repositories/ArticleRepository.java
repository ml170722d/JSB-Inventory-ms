package rs.hnp.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import rs.hnp.inventory.models.Article;

@Transactional
public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByName(String name);

  Optional<Article> findByExternalId(String externalId);

  boolean existsByExternalId(String id);

  boolean existsByName(String id);

}
