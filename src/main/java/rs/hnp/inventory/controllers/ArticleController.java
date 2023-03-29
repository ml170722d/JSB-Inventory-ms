package rs.hnp.inventory.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.services.ArticleService;

@Validated
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

  private final ArticleService articleService;

  @PostMapping("")
  public ResponseEntity<Article> createArticle(@Valid @RequestBody Article article) {
    try {
      return ResponseEntity.ok().body(articleService.createArticle(article));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/multiple")
  public ResponseEntity<List<Article>> createArticles(@Valid @RequestBody List<Article> articles) {
    try {
      return ResponseEntity.ok().body(articleService.createArticles(articles));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
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
  public ResponseEntity<Article> findById(@Valid @PathVariable Long id) {
    Article article = articleService.findById(id).orElseThrow(ResourceNotFoundException::new);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Article>> findByName(@Valid @PathVariable String name) {
    List<Article> list = articleService.findByName(name);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/externalId/{externalId}")
  public ResponseEntity<List<Article>> findByExternalId(@Valid @PathVariable String externalId) {
    List<Article> list = articleService.findByExternalId(externalId);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/distributor/{id}")
  public ResponseEntity<List<Article>> findByDistributor(@Valid @PathVariable Long id) {
    return ResponseEntity.ok().body(articleService.findByDistributor(id));
  }

  @GetMapping("/distributor/name/{name}")
  public ResponseEntity<List<Article>> findByDistributor(@Valid @PathVariable String name) {
    return ResponseEntity.ok().body(articleService.findByDistributor(name));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Article> updateArticle(@Valid @PathVariable Long id,
      @Valid @RequestBody Article updatedArticle) {
    try {
      return ResponseEntity.ok().body(articleService.updateArticle(id, updatedArticle));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteArticle(@Valid @PathVariable Long id) {
    try {
      articleService.deleteArticle(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/multiple")
  public ResponseEntity<Object> deleteArticles(@Valid @RequestBody List<Long> ids) {
    try {
      articleService.deleteArticles(ids);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok().build();
  }
}
