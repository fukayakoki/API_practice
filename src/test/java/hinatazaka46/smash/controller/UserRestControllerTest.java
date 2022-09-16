package hinatazaka46.smash.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hinatazaka46.smash.Controller.UserController;
import hinatazaka46.smash.Domain.User;
import hinatazaka46.smash.service.UserServiceImpl;
import hinatazaka46.smash.util.UnitTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserServiceImpl userService;
    
    @Test
    void getByIdTest_Userを返す() throws Exception {
        User excepted = new User();
        excepted.setId(1);
        doReturn(excepted).when(userService).getById(1);
        
        MvcResult actual = mockMvc.perform(MockMvcRequestBuilders.get("/services/v1/user/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        assertThat(UnitTestUtil.entityToJsonText(excepted)).isEqualTo(
            actual.getResponse().getContentAsString());
        verify(userService, times(1)).getById(1);
    }
}
