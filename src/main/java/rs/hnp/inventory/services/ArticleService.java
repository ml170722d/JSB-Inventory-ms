package rs.hnp.inventory.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rs.hnp.inventory.dto.article.ArticleDTO;
import rs.hnp.inventory.dto.article.ArticleUpdateDTO;
import rs.hnp.inventory.exceptions.ApiException;
import rs.hnp.inventory.exceptions.ApiExceptionFactory;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final ModelMapper modelMapper;

  private Article getArticleById(Long id) {
    return articleRepository.findById(id).orElseThrow(ApiExceptionFactory::articleNotFound);
  }

  /**
   * Create single new article.
   *
   * @param article Article object for creating new record.
   * @return Article object if successful, null otherwise.
   */
  public ArticleDTO createArticle(ArticleDTO article) throws ApiException{
     articleRepository.findByExternalId(article.getExternalId()).ifPresent(
             (articleDTO) -> {throw ApiExceptionFactory.articlePresent();}
     );
     return modelMapper.map(articleRepository.save(modelMapper.map(article, Article.class)), ArticleDTO.class);
  }

  /**
   * Create multiple new articles.
   *
   * @param articles List of Article objects for creating new records.
   * @return List of Article object if successful.
   *         If failed to create all, returns list of Article that were created.
   */
  public List<ArticleDTO> createArticles(List<ArticleDTO> articles) {

    List<Article> newArticles = articles.stream()
        .filter(articleDTO -> articleRepository.findByExternalId(articleDTO.getExternalId()).isEmpty())
        .map(article -> modelMapper.map(article, Article.class)).toList();

    return articleRepository.saveAll(newArticles)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class)).toList();
  }

  /**
   * Get all articles in inventory.
   *
   * @return List of all articles ever inserted.
   */
  public List<ArticleDTO> findAll() {
    return articleRepository.findAll()
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .toList();
  }

  /**
   * Find article by id.
   *
   * @param id ID assigned to Article by the system.
   * @return Article object if found, null otherwise.
   */
  public ArticleDTO findById(Long id) {
    Article article = getArticleById(id);
    return modelMapper.map(article, ArticleDTO.class);
  }

  /**
   * Get all available articles in inventory.
   *
   * @return List of articles that are currently available.
   */
  public List<ArticleDTO> findAllAvailable() {
    return articleRepository.findAllAvailable()
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Find article by name.
   *
   * @param name Name assigned to Article.
   * @return List of Article object with the same name.
   */
  public List<ArticleDTO> findByName(String name) {
    return articleRepository.findByName(name)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .toList();
  }

  /**
   * Find article by external id.
   *
   * @param externalId ID of Article assigned by Distributor.
   * @return List of Article object that match criteria, null otherwise.
   */
  public List<ArticleDTO> findByExternalId(String externalId) {
    return articleRepository.findByExternalId(externalId)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .toList();
  }

  /**
   * Update single article.
   *
   * @param id             Id of article.
   * @param updatedArticle New data that should replace old data.
   * @return New article data if successful, old data otherwise.
   */
  public ArticleDTO updateArticle(Long id, ArticleUpdateDTO updatedArticle) {
    Article existingArticle = getArticleById(id);
    modelMapper.map(updatedArticle, existingArticle);
    return modelMapper.map(articleRepository.save(existingArticle), ArticleDTO.class);
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
   * @param ids List of Article objects that should be deleted.
   */
  public void deleteArticle(List<Long> ids) {
    articleRepository.deleteAllById(ids);
  }
}
