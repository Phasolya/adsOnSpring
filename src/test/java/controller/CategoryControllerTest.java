package controller;

import com.controller.CategoryController;
import com.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.CategoryService;
import configuration.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryControllerTest {

    @Mock
    CategoryService service;

    @InjectMocks
    CategoryController controller;

    static MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void shouldSaveCategory() throws Exception {
        Mockito.doNothing().when(service).save(ArgumentMatchers.any(Category.class));

        Category cars = Category.builder()
                .name("cars")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(cars);

        mockMvc
                .perform(post("/categories/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetCategory() throws Exception {

        Category cars = Category.builder()
                .name("cars")
                .id(1)
                .build();

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(cars);

        mockMvc
                .perform(get("/categories/category/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("cars"));
    }

}
