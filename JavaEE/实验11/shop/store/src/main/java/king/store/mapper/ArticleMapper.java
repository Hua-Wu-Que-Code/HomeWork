package king.store.mapper;

import king.store.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from `article` where id in ( select id from  `item` where order_id =#{id} )")
    List<Article> selectByOrderId(Integer order_id);
}
