package rs.hnp.inventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.services.ArticleService;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

  private final ArticleService articleService;

  /**
   * Create single new article.
   *
   * @param article Article object for creating new record.
   * @return Article object if successful, null otherwise.
   */
  @PostMapping("")
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

  @GetMapping("")
  public ResponseEntity<List<Article>> getAllArticle() {
    return ResponseEntity.ok().body(articleService.findAll());
  }

  @GetMapping("/available")
  public ResponseEntity<List<Article>> getAllAvailableArticle() {
    return ResponseEntity.ok().body(articleService.findAllAvailable());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Article> findById(@PathVariable Long id) {
    Optional<Article> article = articleService.findById(id);
    return ResponseEntity.ok().body(article.get());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Article>> findByName(@PathVariable String name) {
    return ResponseEntity.ok().body(articleService.findByName(name));
  }

  @GetMapping("/externalId/{externalId}")
  public ResponseEntity<List<Article>> findByExternalId(@PathVariable String externalId) {
    return ResponseEntity.ok().body(articleService.findByExternalId(externalId));
  }

  @GetMapping("/distributor/{id}")
  public ResponseEntity<List<Article>> findByDistributor(@PathVariable Long id) {
    return ResponseEntity.ok().body(articleService.findByDistributor(id));
  }

  @GetMapping("/distributor/name/{name}")
  public ResponseEntity<List<Article>> findByDistributor(@PathVariable String name) {
    return ResponseEntity.ok().body(articleService.findByDistributor(name));
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
