package rs.hnp.inventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "external_id")
  private String externalId;
  // @ManyToOne
  // @JoinColumn(name = "distributorId")
  // private Distributor distributor;

  @Column(name = "name")
  private String name;
  @Column(name = "unit")
  private String unit;
  @Column(name = "amount")
  private Long amount;
  @Column(name = "description")
  private String description;

  @Column(name = "buying_price")
  private Long buying_price;
  @Column(name = "selling_price")
  private Long selling_price;
}
