package rs.hnp.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.hnp.inventory.dao.ArticleDAO;
import rs.hnp.inventory.dto.article.ArticleDTO;
import rs.hnp.inventory.exceptions.ApiExceptionFactory;
import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.utils.records.ArticleRegistrationRequest;
import rs.hnp.inventory.utils.records.ArticleUpdateRequest;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ModelMapper modelMapper;
  private final ArticleDAO articleDAO;

  private void checkIfArticleExistsOrThrow(Long id) {
    if (!articleDAO.existsArticleById(id)) {
      throw ApiExceptionFactory.articleNotFound();
    }
  }

  public List<ArticleDTO> getAllArticles() {
    return articleDAO.selectAllArticles()
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .toList();
  }

  public ArticleDTO getArticleById(Long id) {
    Article article = articleDAO.selectArticleById(id).orElseThrow(ApiExceptionFactory::articleNotFound);
    return modelMapper.map(article, ArticleDTO.class);
  }

  public List<ArticleDTO> getAllAvailableArticles() {
    return articleDAO.selectAllArticles()
        .stream()
        .filter(article -> article.getAmount() > 0)
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
  }

  public List<ArticleDTO> getArticleByName(String name) {
    return articleDAO.selectArticleByName(name)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .collect(Collectors.toList());
  }

  public List<ArticleDTO> getArticleByExternalId(String externalId) {
    return articleDAO.selectArticleByExternalId(externalId)
        .stream()
        .map(article -> modelMapper.map(article, ArticleDTO.class))
        .toList();
  }

  public ArticleDTO createArticle(ArticleRegistrationRequest articleRegistrationRequest) {
    String externnalId = articleRegistrationRequest.externalId();
    if (articleDAO.existsArticleWithExternalId(externnalId)) {
      throw ApiExceptionFactory.articlePresent();
    }

    Article article = new Article(
        articleRegistrationRequest.externalId(),
        articleRegistrationRequest.name(),
        articleRegistrationRequest.unit(),
        articleRegistrationRequest.amount(),
        articleRegistrationRequest.description(),
        articleRegistrationRequest.buying_price(),
        articleRegistrationRequest.selling_price());

    articleDAO.insertArticle(article);

    return modelMapper.map(article, ArticleDTO.class);
  }

  public List<ArticleDTO> createArticles(List<ArticleRegistrationRequest> articles) {
    for (ArticleRegistrationRequest articleReq : articles) {
      String externnalId = articleReq.externalId();
      if (articleDAO.existsArticleWithExternalId(externnalId)) {
        throw ApiExceptionFactory.articlePresent();
      }
    }

    List<ArticleDTO> dtos = new ArrayList<>();
    for (ArticleRegistrationRequest articleReq : articles) {
      Article article = new Article(
          articleReq.externalId(),
          articleReq.name(),
          articleReq.unit(),
          articleReq.amount(),
          articleReq.description(),
          articleReq.buying_price(),
          articleReq.selling_price());

      articleDAO.insertArticle(article);

      dtos.add(modelMapper.map(article, ArticleDTO.class));
    }

    return dtos;
  }

  public void deleteArticle(Long id) {
    checkIfArticleExistsOrThrow(id);
    articleDAO.deleteArticleById(id);
  }

  public void deleteArticle(List<Long> ids) {
    ids.forEach(id -> checkIfArticleExistsOrThrow(id));
    ids.forEach(id -> articleDAO.deleteArticleById(id));
  }

  public ArticleDTO updateArticle(Long id, ArticleUpdateRequest updatedArticle) {
    Article article = articleDAO.selectArticleById(id).orElseThrow(ApiExceptionFactory::articleNotFound);

    boolean changes = false;

    if (updatedArticle.name() != null && updatedArticle.name().equals(article.getName())) {
      article.setName(updatedArticle.name());
      changes = true;
    }

    if (updatedArticle.unit() != null && updatedArticle.unit().equals(article.getUnit())) {
      article.setUnit(updatedArticle.unit());
      changes = true;
    }

    if (updatedArticle.amount() != null && updatedArticle.amount().equals(article.getAmount())) {
      article.setAmount(updatedArticle.amount());
      changes = true;
    }

    if (updatedArticle.description() != null && updatedArticle.description().equals(article.getDescription())) {
      article.setDescription(updatedArticle.description());
      changes = true;
    }

    if (updatedArticle.buying_price() != null && updatedArticle.buying_price().equals(article.getBuying_price())) {
      article.setBuying_price(updatedArticle.buying_price());
      changes = true;
    }

    if (updatedArticle.selling_price() != null && updatedArticle.selling_price().equals(article.getSelling_price())) {
      article.setSelling_price(updatedArticle.selling_price());
      changes = true;
    }

    if (changes) {
      articleDAO.updateArticle(article);
    }

    return modelMapper.map(article, ArticleDTO.class);
  }
}
