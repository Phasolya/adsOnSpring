package controller;

import com.controller.CategoryController;
import com.entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.CategoryService;
import config.ConfigAppTest;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryControllerTest {

    @Mock
    CategoryService service;

    @InjectMocks
    CategoryController controller;

    @Autowired
    private Validator validator;

    static MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setControllerAdvice()
                .build();

//        controller.setValidator(validator);

    }

    public static Category getCategory(int id){

        return Category.builder()
                .id(id)
                .name("name"+id)
                .build();
    }

    @Test
    public void shouldSaveCategory() throws Exception {

        Mockito.doNothing().when(service).save(ArgumentMatchers.any(Category.class));

        Category cars = Category.builder()
                .name("saveTest")
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

        Category category = Category.builder()
                .name("getTest")
                .id(1)
                .build();

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(category);

        mockMvc
                .perform(get("/categories/category/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("getTest"));
    }

    @Test
    public void shouldUpdateCategory() throws Exception {

        Mockito.doNothing().when(service).update(ArgumentMatchers.any(Category.class));

        Category updated = Category.builder()
                .name("updateTest")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(updated);

        mockMvc
                .perform(put("/categories/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteCategory() throws Exception {

        Mockito.doNothing().when(service).deleteById(ArgumentMatchers.anyInt());

        mockMvc
                .perform(delete("/categories/category/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetCountAllCategory() throws Exception {

        Mockito.when(service.countAll()).thenReturn(30);

        mockMvc
                .perform(get("/categories/count"))
                .andDo(print())
                .andExpect(jsonPath("$").value(30));
    }

    @Test
    public void shouldGetSortedByName() throws Exception {

        Category c1 = Category.builder()
                .name("c1")
                .build();

        Category c2 = Category.builder()
                .name("c2")
                .build();

        List<Category> categories = Arrays.asList(c1, c2);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(categories);

        Mockito
                .when(service.getSortedByName(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(categories);

        mockMvc
                .perform(get("/categories/all/from{startRow}amount{amount}", 0, 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].name").value(c1.getName()))
                .andExpect(jsonPath("$[1].name").value(c2.getName()));
    }
}
