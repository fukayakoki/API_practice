package hinatazaka46.smash.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hinatazaka46.smash.Domain.User;
import hinatazaka46.smash.repository.mybatis.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class UserMapperTest {
    
    @Autowired
    private UserMapper userMapper;
    
    @Test
    void getByIdTest_statementsのデータを取得できる() {
        User excepted = new User();
        excepted.setId(1);
        excepted.setUserName("mj");
        excepted.setPassword("mj");
        excepted.setImage("mj");
        
        User actual = userMapper.getById(1);
        
        assertThat(excepted).isEqualTo(actual);
    }
}
