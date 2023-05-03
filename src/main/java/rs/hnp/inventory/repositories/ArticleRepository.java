package rs.hnp.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.hnp.inventory.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByName(String name);

  Optional<Article> findByExternalId(String externalId);

}
