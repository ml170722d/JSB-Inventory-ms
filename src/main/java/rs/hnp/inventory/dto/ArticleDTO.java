package rs.hnp.inventory.dto;

import lombok.Data;

@Data
public class ArticleDTO {
  private Long id;
  private String externalId;
  private String name;
  private String unit;
  private Long amount;
  private String description;
  private Long buying_price;
  private Long selling_price;
}
