package rs.hnp.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleService {

  private final ArticleRepository articleRepository;

  /**
   * Create single new article.
   *
   * @param article Article object for creating new record.
   * @return Article object if successful, null otherwise.
   */
  public Article createArticle(Article article) {
    return articleRepository.save(article);
  }

  /**
   * Create multiple new articles.
   *
   * @param articles List of Article objects for creating new records.
   * @return List of Article object if successful.
   *         If failed to create all, returns list of Article that were created.
   */
  public List<Article> createArticles(List<Article> articles) {
    return articleRepository.saveAll(articles);
  }

  /**
   * Get all articles in inventory.
   *
   * @return List of all articles ever inserted.
   */
  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  /**
   * Find article by id.
   *
   * @param id ID assigned to Article by the system.
   * @return Article object if found, null otherwise.
   */
  public Optional<Article> findById(Long id) {
    return articleRepository.findById(id);
  }

  /**
   * Get all available articles in inventory.
   *
   * @return List of articles that are currently available.
   * @throws Exception If processing of articles fails.
   */
  public List<Article> findAllAvailable() {
    return articleRepository.findAllAvailable();
  }

  /**
   * Find article by name.
   *
   * @param name Name assigned to Article.
   * @return List of Article object with the same name.
   * @throws Exception If processing of articles fails.
   */
  public List<Article> findByName(String name) {
    return articleRepository.findByName(name);
  }

  /**
   * Find article by external id.
   *
   * @param externalId ID of Article assigned by Distributor.
   * @return List of Article object that match criteria, null otherwise.
   */
  public List<Article> findByExternalId(String externalId) {
    return articleRepository.findByExternalId(externalId);
  }

  /**
   * Get list of Article objects from specific Distributor.
   *
   * @param id ID assigned to Distributor by the system.
   * @return List of Article object by specified Distributor.
   */
  public List<Article> findByDistributor(Long id) {
    return null;
  }

  /**
   * Get list of Article objects from specific Distributor.
   *
   * @param name Name of Distributor.
   * @return List of Article object by specified Distributor.
   */
  public List<Article> findByDistributor(String name) {
    return null;
  }

  /**
   * Update single article.
   *
   * @param id             Id of article.
   * @param updatedArticle New data that should replace old data.
   * @return New article data if successful, old data otherwise.
   */
  public Article updateArticle(Long id, Article updatedArticle) {
    Article article = findById(id).orElseThrow();
    updatedArticle.setId(article.getId());
    return articleRepository.save(updatedArticle);
  }

  /**
   * Delete single article.
   *
   * @param id of article that should be deleted.
   */
  public void deleteArticle(Long id) {
    articleRepository.deleteById(id);
  }

  /**
   * Delete multiple articles.
   *
   * @param articles List of Article objects that should be deleted.
   */
  public void deleteArticles(List<Long> ids) {
    articleRepository.deleteAllById(ids);
  }
}
