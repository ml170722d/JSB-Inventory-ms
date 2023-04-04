package rs.hnp.inventory.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ArticleUpdateDTO {
  private String externalId;
  private String name;
  private String unit;
  private Long amount;
  private String description;
  private Long buying_price;
  private Long selling_price;
}
