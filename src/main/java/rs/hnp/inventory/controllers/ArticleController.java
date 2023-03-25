package rs.hnp.inventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping("")
  public ResponseEntity<Article> createArticle(Article article) {
    return null;
  }

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

  @PutMapping("/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
    return ResponseEntity.ok().body(articleService.updateArticle(id, updatedArticle));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
    articleService.deleteArticle(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/multiple")
  public ResponseEntity<Object> deleteArticles(@RequestBody List<Long> ids) {
    articleService.deleteArticles(ids);
    return ResponseEntity.ok().build();
  }
}
