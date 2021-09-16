package controller;

import com.controller.MatchingAdController;
import com.entity.MatchingAd;
import com.entity.Role;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.MatchingAdService;
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
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.math.BigDecimal;
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
public class MatchingAdControllerTest {

    @Mock
    MatchingAdService service;

    @InjectMocks
    MatchingAdController controller;

    static MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    public static MatchingAd getMatchingAd(int id) {

        return MatchingAd.builder()
                .id(id)
                .title("title" + id)
                .priceFrom(BigDecimal.ONE)
                .priceTo(BigDecimal.TEN)
                .user(UserControllerTest.getUser(id))
                .category(CategoryControllerTest.getCategory(id))
                .build();
    }

    @Test
    public void shouldSaveMatchingAd() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(getMatchingAd(1));

        Mockito.doNothing().when(service).save(ArgumentMatchers.any(MatchingAd.class));

        mockMvc
                .perform(post("/mads/mad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetMatchingAd() throws Exception {

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(getMatchingAd(1));

        mockMvc
                .perform(get("/mads/mad/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("title1"))
                .andExpect(jsonPath("priceFrom").value(BigDecimal.ONE))
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.category.id").value(1));
    }

    @Test
    public void shouldUpdateMatchingAd() throws Exception {

        Mockito.doNothing().when(service).update(ArgumentMatchers.any(MatchingAd.class));

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(getMatchingAd(1));

        mockMvc
                .perform(put("/mads/mad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteMatchingAd() throws Exception {

        Mockito.doNothing().when(service).deleteById(ArgumentMatchers.anyInt());

        mockMvc
                .perform(delete("/mads/mad/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetMadByUserId() throws Exception {

        MatchingAd mad1 = getMatchingAd(1);
        MatchingAd mad2 = getMatchingAd(2);

        List<MatchingAd> mads = Arrays.asList(mad1, mad2);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(mads);

        Mockito
                .when(service.getByUserOrderById(ArgumentMatchers.anyInt()))
                .thenReturn(mads);

        mockMvc
                .perform(get("/mads/by-user{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].title").value(mad1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(mad2.getTitle()));
    }

}
