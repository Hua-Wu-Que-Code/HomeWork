package king.store.mapper;

import king.store.entity.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface OrderMapper {

    @Select("select * from `order` where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "code",property = "code"),
            @Result(column = "total",property = "total"),
            @Result(column = "user_id",property = "user",
                    one = @One(select = "king.store.mapper.UserMapper.selectById",
                            fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "articles",
                many = @Many(select = "king.store.mapper.ArticleMapper.selectByOrderId"))
    })
    Order selectById(Integer id);
}
