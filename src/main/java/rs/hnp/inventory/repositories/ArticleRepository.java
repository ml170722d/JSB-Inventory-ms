package rs.hnp.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.hnp.inventory.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
