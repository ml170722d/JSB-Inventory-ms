package rs.hnp.inventory.models;

import java.sql.ResultSet;
import java.sql.SQLException;

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

  @Column(name = "externalId")
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

  @Column(name = "buyingPrice")
  private Long buyingPrice;
  @Column(name = "sellingPrice")
  private Long sellingPrice;

  public Article(ResultSet rs) throws SQLException {
    this.id = rs.getLong("id");
    this.externalId = rs.getString("externalId");
    // this.distributor = new Distributor(rs.getLong("distributorId"),
    // rs.getString("distributorName"));
    this.name = rs.getString("name");
    this.unit = rs.getString("unit");
    this.amount = rs.getLong("amount");
    this.description = rs.getString("description");
    this.buyingPrice = rs.getLong("buyingPrice");
    this.sellingPrice = rs.getLong("sellingPrice");
  }
}
