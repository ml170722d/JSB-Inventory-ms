package rs.hnp.inventory.mappers;

import org.modelmapper.ModelMapper;

public class ArticleDTOMapper extends ModelMapper {
  public ArticleDTOMapper() {
    this.getConfiguration().setSkipNullEnabled(true);
  }
}
