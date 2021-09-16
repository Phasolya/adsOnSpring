package controller;

import com.controller.UserController;
import com.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController controller;

    static MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    public static User getUser(int id){

        Phone phone = Phone.builder()
                .id(id)
                .phoneNumber("phone" + id)
                .build();

        Email email = Email.builder()
                .id(id)
                .mailAddress("mail" + id)
                .build();

        Address address = Address.builder()
                .id(id)
                .street("street" + id)
                .region("region" + id)
                .building("building" + id)
                .city("city" + id)
                .build();

        return User.builder()
                .id(id)
                .login("login"+id)
                .password("pass"+id)
                .registration(LocalDate.now())
                .firstName("firstName"+id)
                .lastName("lastName"+id)
                .role(Role.USER)
                .phone(phone)
                .email(email)
                .address(address)
                .build();

    }

    @Test
    public void shouldSaveUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(getUser(0));

        Mockito.doNothing().when(service).save(ArgumentMatchers.any(User.class));

        mockMvc
                .perform(post("/users/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetUser() throws Exception {

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(getUser(1));

        mockMvc
                .perform(get("/users/user/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("login").value("login1"))
                .andExpect(jsonPath("password").value("pass1"));
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        Mockito.doNothing().when(service).update(ArgumentMatchers.any(User.class));

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(getUser(1));

        mockMvc
                .perform(put("/users/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        Mockito.doNothing().when(service).deleteById(ArgumentMatchers.anyInt());

        mockMvc
                .perform(delete("/users/user/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetCountAllUsers() throws Exception {

        Mockito.when(service.countAll()).thenReturn(30);

        mockMvc
                .perform(get("/users/count"))
                .andDo(print())
                .andExpect(jsonPath("$").value(30));
    }

    @Test
    public void shouldGetSortedByName() throws Exception {

        User user1 = getUser(1);
        User user2 = getUser(2);

        List<User> users = Arrays.asList(user1, user2);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(users);

        Mockito
                .when(service.findAllOrderByRegistration(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(users);

        mockMvc
                .perform(get("/users/from{startRow}amount{amount}", 0, 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].login").value(user1.getLogin()))
                .andExpect(jsonPath("$[1].login").value(user2.getLogin()))
                .andExpect(jsonPath("$[0].password").value(user1.getPassword()))
                .andExpect(jsonPath("$[1].password").value(user2.getPassword()));
    }
}
