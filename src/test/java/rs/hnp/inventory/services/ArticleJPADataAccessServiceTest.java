package rs.hnp.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

public class ArticleJPADataAccessServiceTest {
  private ArticleJPADataAccessService jpaDataAccessService;
  private AutoCloseable autoCloseable;
  @Mock
  private ArticleRepository articleRepository;

  @BeforeEach
  void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
    jpaDataAccessService = new ArticleJPADataAccessService(articleRepository);
  }

  @AfterEach
  void tearDowd() throws Exception {
    autoCloseable.close();
  }

  @Test
  public void selectAllArticles() {
    Page<Article> page = mock(Page.class);
    List<Article> articles = List.of(new Article());
    when(page.getContent()).thenReturn(articles);
    when(articleRepository.findAll(any(Pageable.class))).thenReturn(page);

    // act
    List<Article> expected = jpaDataAccessService.selectAllArticles();

    // assert
    assertThat(expected).isEqualTo(articles);
    ArgumentCaptor<Pageable> pageArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
    verify(articleRepository).findAll(pageArgumentCaptor.capture());
    assertThat(pageArgumentCaptor.getValue()).isEqualTo(Pageable.ofSize(100));
  }
}
