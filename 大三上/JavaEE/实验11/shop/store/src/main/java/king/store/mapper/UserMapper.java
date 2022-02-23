package king.store.mapper;

import king.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from `user` where id = #{id}")
    User selectById(Integer id);
}
