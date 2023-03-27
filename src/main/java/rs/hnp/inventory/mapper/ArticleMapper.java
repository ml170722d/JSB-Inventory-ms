package rs.hnp.inventory.mapper;

import org.mapstruct.Mapper;

import rs.hnp.inventory.dto.ArticleDTO;
import rs.hnp.inventory.models.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

  ArticleDTO toDto(Article article);

  Article toEntity(ArticleDTO articleDto);

}
