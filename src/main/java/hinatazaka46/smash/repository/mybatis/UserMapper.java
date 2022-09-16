package hinatazaka46.smash.repository.mybatis;

import hinatazaka46.smash.Domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    
    User getById(@Param("userId") int id);
    
    int add(User user);
}
