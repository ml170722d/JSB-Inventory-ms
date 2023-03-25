package rs.hnp.inventory.models;

// import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "distributors")
public class Distributor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;
  @Column(name = "contact")
  private String contact;
  @Column(name = "website")
  private String website;
  @Column(name = "description")
  private String description;

  // @OneToMany(mappedBy = "distributor")
  // private List<Article> articles;
}
