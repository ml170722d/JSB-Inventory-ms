package rs.hnp.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.dao.ArticleDAO;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

@Repository("jpa")
@RequiredArgsConstructor
public class ArticleJPADataAccessService implements ArticleDAO {

  private final ArticleRepository articleRepository;

  @Override
  public List<Article> selectAllArticles() {
    Page<Article> page = articleRepository.findAll(Pageable.ofSize(100));
    return page.getContent();
  }

  @Override
  public Optional<Article> selectArticleById(Long articleId) {
    return articleRepository.findById(articleId);
  }

  @Override
  public Optional<Article> selectArticleByExternalId(String externalId) {
    return articleRepository.findByExternalId(externalId);
  }

  @Override
  public List<Article> selectArticleByName(String name) {
    return articleRepository.findByName(name);
  }

  @Override
  public void insertArticle(Article article) {
    articleRepository.save(article);
  }

  @Override
  public boolean existsArticleById(Long articleId) {
    return articleRepository.existsById(articleId);
  }

  @Override
  public boolean existsArticleWithExternalId(String externalId) {
    return articleRepository.existsByExternalId(externalId);
  }

  @Override
  public boolean existsArticleWithName(String name) {
    return articleRepository.existsByName(name);
  }

  @Override
  public void deleteArticleById(Long articleId) {
    articleRepository.deleteById(articleId);
  }

  @Override
  public void updateArticle(Article update) {
    articleRepository.save(update);
  }

}
