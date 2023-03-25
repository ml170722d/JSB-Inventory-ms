package rs.hnp.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.hnp.inventory.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
  List<Article> findByAmount(Long amount);

  @Query("SELECT a FROM Article a WHERE a.amount > 0")
  List<Article> findAllAvailable();

  List<Article> findByName(String name);

  List<Article> findByExternalId(String externalId);

  @Modifying
  @Query("UPDATE Article a SET a.name = :name, a.unit = :unit, a.amount = :amount, a.description = :description, a.buyingPrice = :buyingPrice, a.sellingPrice = :sellingPrice WHERE a.id = :id")
  Article updateArticle(
      @Param("id") Long id,
      @Param("name") String name,
      @Param("unit") String unit,
      @Param("amount") Long amount,
      @Param("description") String description,
      @Param("buyingPrice") Long buyingPrice,
      @Param("sellingPrice") Long sellingPrice);
}
