package rs.hnp.inventory.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.dto.article.ArticleDTO;
import rs.hnp.inventory.exceptions.ApiExceptionFactory;
import rs.hnp.inventory.services.ArticleService;
import rs.hnp.inventory.utils.records.ArticleRegistrationRequest;
import rs.hnp.inventory.utils.records.ArticleUpdateRequest;

@Validated
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

  private final ArticleService articleService;

  @PostMapping(value = "")
  public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleRegistrationRequest article) {
    return ResponseEntity.ok(articleService.createArticle(article));
  }

  @PostMapping(value = "/multiple")
  public ResponseEntity<List<ArticleDTO>> createArticles(
      @Valid @RequestBody List<ArticleRegistrationRequest> articles) {
    return ResponseEntity.ok(articleService.createArticles(articles));
  }

  @GetMapping(value = "")
  public ResponseEntity<List<ArticleDTO>> getAllArticle() {
    List<ArticleDTO> list = articleService.getAllArticles();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/available")
  public ResponseEntity<List<ArticleDTO>> getAllAvailableArticle() {
    List<ArticleDTO> list = articleService.getAllAvailableArticles();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ArticleDTO> getById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok().body(articleService.getArticleById(id));
  }

  @GetMapping(value = "/name/{name}")
  public ResponseEntity<List<ArticleDTO>> getByName(@Valid @PathVariable String name) {
    List<ArticleDTO> list = articleService.getArticleByName(name);
    if (list.isEmpty())
      throw ApiExceptionFactory.articleNotFound();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/externalId/{externalId}")
  public ResponseEntity<List<ArticleDTO>> findByExternalId(@Valid @PathVariable String externalId) {
    List<ArticleDTO> list = articleService.getArticleByExternalId(externalId);
    if (list.isEmpty())
      throw ApiExceptionFactory.articleNotFound();
    return ResponseEntity.ok().body(list);
  }

  @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<ArticleDTO> updateArticle(
      @Valid @PathVariable Long id,
      @Valid @RequestBody ArticleUpdateRequest updatedArticle) {
    return ResponseEntity.ok(articleService.updateArticle(id, updatedArticle));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteArticle(@Valid @PathVariable Long id) {
    try {
      articleService.deleteArticle(id);
    } catch (Exception e) {
      throw ApiExceptionFactory.articleNotExists();
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping(value = "/multiple")
  public ResponseEntity<Object> deleteArticles(@Valid @RequestBody List<Long> ids) {
    try {
      articleService.deleteArticle(ids);
    } catch (Exception e) {
      throw ApiExceptionFactory.articleNotExists();
    }
    return ResponseEntity.ok().build();
  }
}
