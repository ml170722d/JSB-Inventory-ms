package rs.hnp.inventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_seq")
  private Long id;

  @Column(name = "external_id", unique = true)
  private String externalId;

  @Column(name = "name")
  private String name;
  @Column(name = "unit")
  private String unit;
  @Column(name = "amount")
  private Long amount;
  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "buying_price")
  private Long buying_price;
  @Column(name = "selling_price")
  private Long selling_price;

  public Article(
      String externalId,
      String name,
      String unit,
      Long amount,
      String description,
      Long buying_price,
      Long selling_price) {
    this.externalId = externalId;
    this.name = name;
    this.unit = unit;
    this.amount = amount;
    this.description = description;
    this.buying_price = buying_price;
    this.selling_price = selling_price;
  }
}
