package rs.hnp.inventory.dao;

import java.util.List;
import java.util.Optional;

import rs.hnp.inventory.models.Article;

public interface ArticleDAO {
  List<Article> selectAllArticles();

  Optional<Article> selectArticleById(Long articleId);

  Optional<Article> selectArticleByExternalId(String articleId);

  List<Article> selectArticleByName(String name);

  Article insertArticle(Article article);

  boolean existsArticleById(Long ArticleId);

  boolean existsArticleWithExternalId(String externalId);

  boolean existsArticleWithName(String name);

  void deleteArticleById(Long ArticleId);

  Article updateArticle(Article update);

}
