package rs.hnp.inventory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.dto.article.ArticleDTO;
import rs.hnp.inventory.dto.article.ArticleUpdateDTO;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final ModelMapper modelMapper;

  private Article getArticleById(Long id) {
    return articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * Create single new article.
   *
   * @param article Article object for creating new record.
   * @return Article object if successful, null otherwise.
   */
  public ArticleDTO createArticle(ArticleDTO article) {
    Article newArticle = modelMapper.map(article, Article.class);
    ArticleDTO dto = modelMapper.map(articleRepository.save(newArticle), ArticleDTO.class);
    return dto;
  }

  /**
   * Create multiple new articles.
   *
   * @param articles List of Article objects for creating new records.
   * @return List of Article object if successful.
   *         If failed to create all, returns list of Article that were created.
   */
  public List<ArticleDTO> createArticle(List<ArticleDTO> articles) {
    List<Article> list = articles.stream()
        .map(article -> modelMapper.map(article, Article.class))
        .collect(Collectors.toList());

    List<ArticleDTO> saved = articleRepository.saveAll(list)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
    return saved;
  }

  /**
   * Get all articles in inventory.
   *
   * @return List of all articles ever inserted.
   */
  public List<ArticleDTO> findAll() {
    List<ArticleDTO> list = articleRepository.findAll()
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
    return list;
  }

  /**
   * Find article by id.
   *
   * @param id ID assigned to Article by the system.
   * @return Article object if found, null otherwise.
   */
  public ArticleDTO findById(Long id) {
    Article article = getArticleById(id);
    ArticleDTO dto = modelMapper.map(article, ArticleDTO.class);
    return dto;
  }

  /**
   * Get all available articles in inventory.
   *
   * @return List of articles that are currently available.
   * @throws Exception If processing of articles fails.
   */
  public List<ArticleDTO> findAllAvailable() {
    List<ArticleDTO> list = articleRepository.findAllAvailable()
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
    return list;
  }

  /**
   * Find article by name.
   *
   * @param name Name assigned to Article.
   * @return List of Article object with the same name.
   * @throws Exception If processing of articles fails.
   */
  public List<ArticleDTO> findByName(String name) {
    List<ArticleDTO> list = articleRepository.findByName(name)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
    return list;
  }

  /**
   * Find article by external id.
   *
   * @param externalId ID of Article assigned by Distributor.
   * @return List of Article object that match criteria, null otherwise.
   */
  public List<ArticleDTO> findByExternalId(String externalId) {
    List<ArticleDTO> list = articleRepository.findByExternalId(externalId)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
    return list;
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
    ArticleDTO dto = modelMapper.map(articleRepository.save(existingArticle), ArticleDTO.class);
    return dto;
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
  public void deleteArticle(List<Long> ids) {
    articleRepository.deleteAllById(ids);
  }
}
