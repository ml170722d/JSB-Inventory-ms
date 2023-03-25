package rs.hnp.inventory.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.services.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleService articleService = new ArticleService();

  /**
   * Create single new article.
   *
   * @param article Article object for creating new record.
   * @return Article object if successful, null otherwise.
   */
  @PostMapping("/")
  public ResponseEntity<Article> createArticle(Article article) {
    return null;
  }

  /**
   * Create multiple new articles.
   *
   * @param articles List of Article objects for creating new records.
   * @return List of Article object if successful.
   *         If failed to create all, returns list of Article that were created.
   */
  @PostMapping("/multiple")
  public ResponseEntity<Article> createArticles(@RequestBody List<Article> articles) {
    return null;
  }

  @GetMapping("/")
  public ResponseEntity<List<Article>> getAllArticle() {
    try {
      return ResponseEntity.ok().body(this.articleService.findAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/available")
  public ResponseEntity<List<Article>> getAllAvailableArticle() {
    try {
      return ResponseEntity.ok().body(this.articleService.findAllAvailable());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Article> findById(@PathVariable Long id) {
    try {
      Optional<Article> article = this.articleService.findById(id);
      if (article.isPresent())
        return ResponseEntity.ok().body(article.get());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Article>> findByName(@PathVariable String name) {
    try {
      return ResponseEntity.ok().body(this.articleService.findByName(name));
    } catch (IllegalArgumentException e) {
      LOG.error(e.getMessage(), e);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Find article by external id.
   *
   * @param externalId ID of Article assigned by Distributor.
   * @return List of Article object that match criteria, null otherwise.
   */
  @GetMapping("/externalId/{externalId}")
  public List<Article> findByExternalId(@PathVariable String externalId) {
    return null;
  }

  /**
   * Get list of Article objects from specific Distributor.
   *
   * @param id ID assigned to Distributor by the system.
   * @return List of Article object by specified Distributor.
   */
  @GetMapping("/distributor/{id}")
  public List<Article> getByDistributor(@PathVariable Long id) {
    return null;
  }

  /**
   * Get list of Article objects from specific Distributor.
   *
   * @param name Name of Distributor.
   * @return List of Article object by specified Distributor.
   */
  @GetMapping("/distributor/name/{name}")
  public List<Article> getByDistributor(@PathVariable String name) {
    return null;
  }

  /**
   * Update single article.
   *
   * @param oldArticle Old data that should be changed.
   * @param newArticle New data that should replace old data.
   * @return New article data if successful, old data otherwise.
   */
  @PutMapping("/{id}")
  public Article updateArticle(@PathVariable Long id, @RequestBody Article newArticle) {
    return null;
  }

  /**
   * Delete single article.
   *
   * @param id of article that should be deleted.
   * @return Article id that was deleted if successful, null otherwise.
   */
  @DeleteMapping("/{id}")
  public Long deleteArticle(@PathVariable Long id) {
    return null;
  }

  /**
   * Delete multiple articles.
   *
   * @param articles List of Article objects that should be deleted.
   * @return List of Article objects that were deleted if successful.
   *         If failed to delete all, returns list of Article that were deleted.
   */
  @DeleteMapping("/multiple")
  public List<Long> deleteArticle(@RequestBody List<Long> ids) {
    return null;
  }
}
