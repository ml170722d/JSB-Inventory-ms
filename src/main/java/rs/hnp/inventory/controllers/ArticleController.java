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
import rs.hnp.inventory.dto.article.ArticleUpdateDTO;
import rs.hnp.inventory.services.ArticleService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

  private final ArticleService articleService;

  @PostMapping(value = "")
  public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO article) {
      return ResponseEntity.ok(articleService.createArticle(article));
  }

  @PostMapping(value = "/multiple")
  public ResponseEntity<List<ArticleDTO>> createArticles(@Valid @RequestBody List<ArticleDTO> articles) {
      return ResponseEntity.ok(articleService.createArticles(articles));
  }

  @GetMapping(value = "")
  public ResponseEntity<List<ArticleDTO>> getAllArticle() {
    List<ArticleDTO> list = articleService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/available")
  public ResponseEntity<List<ArticleDTO>> getAllAvailableArticle() {
    List<ArticleDTO> list = articleService.findAllAvailable();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ArticleDTO> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok().body(articleService.findById(id));
  }

  @GetMapping(value = "/name/{name}")
  public ResponseEntity<List<ArticleDTO>> findByName(@Valid @PathVariable String name) {
    List<ArticleDTO> list = articleService.findByName(name);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/externalId/{externalId}")
  public ResponseEntity<List<ArticleDTO>> findByExternalId(@Valid @PathVariable String externalId) {
    List<ArticleDTO> list = articleService.findByExternalId(externalId);
    if (list.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().body(list);
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ArticleDTO> updateArticle(
          @Valid @PathVariable Long id,
          @Valid @RequestBody ArticleUpdateDTO updatedArticle
  ) {
    return ResponseEntity.ok(articleService.updateArticle(id, updatedArticle));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteArticle(@Valid @PathVariable Long id) {
    try {
      articleService.deleteArticle(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping(value = "/multiple")
  public ResponseEntity<Object> deleteArticles(@Valid @RequestBody List<Long> ids) {
    try {
      articleService.deleteArticle(ids);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok().build();
  }
}
