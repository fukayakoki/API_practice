package hinatazaka46.smash.Exception;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;

import hinatazaka46.smash.Controller.UserController;
import hinatazaka46.smash.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {UserController.class})
class ApiExceptionHandlerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserServiceImpl userService;
    
    @Test
    void handleResourceNotFoundExceptionTest_404エラーを返す() throws Exception {
        ResourceNotFoundException exception = new ResourceNotFoundException("User not Foundだよ");
        doThrow(exception).when(userService).getById(1000);
        
        MvcResult actual = mockMvc.perform(MockMvcRequestBuilders.get("/services/v1/user/1000"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andReturn();
        
        System.out.println(actual);
        assertThat("Resource Not Found").isEqualTo(actual.getResponse().getContentAsString());
        assertThat(exception).isEqualTo(actual.getResolvedException());
    }
}