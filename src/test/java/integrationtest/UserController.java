package integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dal.zeynep.miniklarna.Application;
import dal.zeynep.miniklarna.model.SignUpRequest;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class UserController {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "spring")
    @Test
    public void shouldReturnTotalDebtOfAuthenticatedUser() throws Exception {
        given(userService.getUserDebt("spring")).willReturn(30);

        mvc.perform(get("/getUserDebt")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalDebt", is(30)));
    }

    @Test
    public void shouldReturn401ErrorWhenGettingTotalDebtOfUnauthenticatedUser() throws Exception {
        mvc.perform(get("/getUserDebt")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnUserEmailAfterSuccessfulSignUp() throws Exception {
        User user = new User("testUser" , "testPassword");
        given(userService.newUser("testUser", "testPassword")).willReturn(user);

        mvc.perform(MockMvcRequestBuilders
                .post("/signUp")
                .content(asJsonString(new SignUpRequest("testUser", "testPassword")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("userEmail", is("testUser")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
