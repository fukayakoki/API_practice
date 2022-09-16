package hinatazaka46.smash.repository;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hinatazaka46.smash.Domain.User;
import hinatazaka46.smash.Exception.ResourceNotFoundException;
import hinatazaka46.smash.repository.mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserRepositoryImplTest {
    
    @Mock
    private SqlSession sqlSession;
    
    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserRepositoryImpl userRepository;
    
    private AutoCloseable mocks;
    
    @BeforeEach
    void before() {
        mocks = MockitoAnnotations.openMocks(this);
        doReturn(userMapper).when(sqlSession).getMapper(UserMapper.class);
    }
    
    @AfterEach
    void after() throws Exception {
        verify(sqlSession, times(1)).getMapper(UserMapper.class);
        mocks.close();
    }
    
    @Test
    void getByIdTest_指定したIDのUserを返す() {
        User excepted = new User();
        excepted.setId(1);
        doReturn(excepted).when(userMapper).getById(1);
        
        User actual = userRepository.getById(1);
        
        assertThat(excepted).isEqualTo(actual);
        verify(userMapper, times(1)).getById(1);
    }
    
    @Test
    void getById_Userが見つからずResourceNotFoundExceptionを投げる() {
        assertThrows(ResourceNotFoundException.class, () -> userRepository.getById(100000));
    }
    
}
