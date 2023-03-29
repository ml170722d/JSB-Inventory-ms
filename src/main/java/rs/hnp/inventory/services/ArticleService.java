package rs.hnp.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    try {
      return articleRepository.save(article);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Create multiple new articles.
   *
   * @param articles List of Article objects for creating new records.
   * @return List of Article object if successful.
   *         If failed to create all, returns list of Article that were created.
   */
  public List<Article> createArticles(List<Article> articles) {
    try {
      return articleRepository.saveAll(articles);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
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
  public Article findById(Long id) {
    return articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
    List<Article> list = articleRepository.findByName(name);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return list;
  }

  /**
   * Find article by external id.
   *
   * @param externalId ID of Article assigned by Distributor.
   * @return List of Article object that match criteria, null otherwise.
   */
  public List<Article> findByExternalId(String externalId) {
    List<Article> list = articleRepository.findByExternalId(externalId);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return list;
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
    Article article = findById(id);
    updatedArticle.setId(article.getId());
    return articleRepository.save(updatedArticle);
  }

  /**
   * Delete single article.
   *
   * @param id of article that should be deleted.
   */
  public void deleteArticle(Long id) {
    try {
      articleRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Delete multiple articles.
   *
   * @param articles List of Article objects that should be deleted.
   */
  public void deleteArticles(List<Long> ids) {
    try {
      articleRepository.deleteAllById(ids);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
