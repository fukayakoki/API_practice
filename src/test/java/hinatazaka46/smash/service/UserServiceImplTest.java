package hinatazaka46.smash.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hinatazaka46.smash.Domain.User;
import hinatazaka46.smash.repository.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceImplTest {
    
    @Mock
    private UserRepositoryImpl userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    private AutoCloseable mocks;
    
    @BeforeEach
    void before() {
        mocks = MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    void after() throws Exception {
        mocks.close();
    }
    
    @Test
    void getByIdTest_Userを返す() {
        User excepted = new User();
        excepted.setId(1);
        doReturn(excepted).when(userRepository).getById(1);
        
        User actual = userService.getById(1);
        
        assertThat(excepted).isEqualTo(actual);
        verify(userRepository, times(1)).getById(1);
    }
    
}