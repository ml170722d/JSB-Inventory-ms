package rs.hnp.inventory.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rs.hnp.inventory.dto.article.ArticleDTO;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.services.ArticleService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

  private final ArticleService articleService;

  @PostMapping("")
  public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO article) {
    try {
      ArticleDTO created = articleService.createArticle(article);
      return ResponseEntity.ok().body(created);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/multiple")
  public ResponseEntity<List<ArticleDTO>> createArticles(@Valid @RequestBody List<ArticleDTO> articles) {
    try {
      List<ArticleDTO> list = articleService.createArticle(articles);
      return ResponseEntity.ok().body(list);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("")
  public ResponseEntity<List<ArticleDTO>> getAllArticle() {
    List<ArticleDTO> list = articleService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/available")
  public ResponseEntity<List<ArticleDTO>> getAllAvailableArticle() {
    List<ArticleDTO> list = articleService.findAllAvailable();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> findById(@Valid @PathVariable Long id) {
    ArticleDTO article = articleService.findById(id);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<ArticleDTO>> findByName(@Valid @PathVariable String name) {
    List<ArticleDTO> list = articleService.findByName(name);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/externalId/{externalId}")
  public ResponseEntity<List<ArticleDTO>> findByExternalId(@Valid @PathVariable String externalId) {
    List<ArticleDTO> list = articleService.findByExternalId(externalId);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().body(list);
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Article> updateArticle(
          @Valid @PathVariable Long id,
          @Valid @RequestBody Article updatedArticle
  ) {
    return ResponseEntity.ok(articleService.updateArticle(id, updatedArticle));
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
      articleService.deleteArticle(ids);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok().build();
  }
}
