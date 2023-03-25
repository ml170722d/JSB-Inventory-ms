package rs.hnp.inventory.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.hnp.inventory.models.Article;
import rs.hnp.inventory.repositories.ArticleRepository;

@Service
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private DataSource dataSource;

  private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);

  /**
   * Get all articles in inventory.
   *
   * @return List of all articles ever inserted.
   */
  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  /**
   * Find article by id.
   *
   * @param id ID assigned to Article by the system.
   * @return Article object if found, null otherwise.
   */
  public Optional<Article> findById(Long id) {
    return articleRepository.findById(id);
  }

  /**
   * Get all available articles in inventory.
   *
   * @return List of articles that are currently available.
   * @throws Exception If processing of articles fails.
   */
  public List<Article> findAllAvailable() throws SQLException {
    String query = "SELECT * FROM articles AS a WHERE a.amount > 0";
    List<Article> list = new ArrayList<>();

    try (Connection connection = this.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        list.add(new Article(rs));
      }
    } catch (SQLException e) {
      LOG.error("Error fetching currently available articals", e);
      throw e;
    }

    return list;
  }

  /**
   * Find article by name.
   *
   * @param name Name assigned to Article.
   * @return List of Article object with the same name.
   * @throws Exception If processing of articles fails.
   */
  public List<Article> findByName(String name) throws Exception, IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name parameter cannot be null or empty.");
    }

    String query = "SELECT * FROM articles AS a WHERE a.name LIKE ?";
    List<Article> list = new ArrayList<>();

    try (Connection connection = this.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {

      preparedStatement.setString(1, "%" + name + "%");
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        list.add(new Article(rs));
      }
    } catch (Exception e) {
      LOG.error("Error fetching articl(s) by name", e);
      throw e;
    }

    return list;
  }
}
