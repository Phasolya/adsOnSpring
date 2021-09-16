package controller;

import com.controller.AdvertisementController;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.entity.Advertisement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AdvertisementService;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
public class AdvertisementControllerTest {

    @Mock
    AdvertisementService service;

    @InjectMocks
    AdvertisementController controller;

    static MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    public static Advertisement getAds(int id){
        return Advertisement.builder()
                .id(id)
                .isActive(true)
                .user(UserControllerTest.getUser(id))
                .publicationDate(LocalDate.now())
                .price(BigDecimal.ONE)
                .header("header"+id)
                .description("description"+id)
                .category(CategoryControllerTest.getCategory(id))
                .build();
    }

    @Test
    public void shouldSaveAdvertisement() throws Exception {

        Mockito.doNothing().when(service).saveAndSentNotifications(ArgumentMatchers.any(Advertisement.class));

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(getAds(1));

        mockMvc
                .perform(post("/ads/ad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetAds() throws Exception {

        Advertisement ads = getAds(1);

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(ads);

        mockMvc
                .perform(get("/ads/ad/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("header").value(ads.getHeader()));
    }

    @Test
    public void shouldUpdateAds() throws Exception {

        Mockito.doNothing().when(service).updateAndSentNotifications(ArgumentMatchers.any(Advertisement.class));

        Advertisement ads = getAds(1);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(ads);

        mockMvc
                .perform(put("/ads/ad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteAds() throws Exception {

        Mockito.doNothing().when(service).deleteById(ArgumentMatchers.anyInt());

        mockMvc
                .perform(delete("/ads/ad/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldGetCountAllAds() throws Exception {

        Mockito.when(service.countAll()).thenReturn(30);

        mockMvc
                .perform(get("/ads/count"))
                .andDo(print())
                .andExpect(jsonPath("$").value(30));
    }

    @Test
    public void shouldGetByCategoryDto() throws Exception {

        Advertisement ads1 = getAds(1);
        Advertisement ads2 = getAds(2);
        List<Advertisement> advertisements = Arrays.asList(ads1, ads2);

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(1)
                .amount(2)
                .startRow(0)
                .sortDirection("ASC")
                .sortParam("name")
                .build();

        Page<Advertisement> pageAds = new PageImpl<Advertisement>(advertisements);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(categoryDto);

        Mockito
                .when(service.getByCategoryDto(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(pageAds);

        mockMvc
                .perform(post("/ads/ad-page-c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content[0].header").value(ads1.getHeader()))
                .andExpect(jsonPath("$.content[1].header").value(ads2.getHeader()));
    }

    @Test
    public void shouldGetByCategoryAndHeaderDto() throws Exception {

        Advertisement ads1 = getAds(1);
        Advertisement ads2 = getAds(2);
        List<Advertisement> advertisements = Arrays.asList(ads1, ads2);

        CategoryHeaderDto categoryHeaderDto = CategoryHeaderDto.builder()
                .categoryId(1)
                .amount(2)
                .startRow(0)
                .sortDirection("ASC")
                .sortParam("name")
                .header("header1")
                .build();

        Page<Advertisement> pageAds = new PageImpl<Advertisement>(advertisements);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(categoryHeaderDto);

        Mockito
                .when(service.getByCategoryHeaderDto(ArgumentMatchers.any(CategoryHeaderDto.class)))
                .thenReturn(pageAds);

        mockMvc
                .perform(post("/ads/ad-page-ch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content[0].header").value(ads1.getHeader()))
                .andExpect(jsonPath("$.content[1].header").value(ads2.getHeader()));
    }

    @Test
    public void shouldGetByCategoryAndHeaderPriceDto() throws Exception {

        Advertisement ads1 = getAds(1);
        Advertisement ads2 = getAds(2);
        List<Advertisement> advertisements = Arrays.asList(ads1, ads2);

        CategoryHeaderPriceDto chpDto = CategoryHeaderPriceDto.builder()
                .categoryId(1)
                .amount(2)
                .startRow(0)
                .priceFrom(BigDecimal.ONE)
                .priceTo(BigDecimal.TEN)
                .sortDirection("ASC")
                .sortParam("name")
                .header("header1")
                .build();

        Page<Advertisement> pageAds = new PageImpl<Advertisement>(advertisements);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(chpDto);

        Mockito
                .when(service.getByCategoryHeaderPriceDto(ArgumentMatchers.any(CategoryHeaderPriceDto.class)))
                .thenReturn(pageAds);

        mockMvc
                .perform(post("/ads/ad-page-chp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content[0].header").value(ads1.getHeader()))
                .andExpect(jsonPath("$.content[1].header").value(ads2.getHeader()));
    }

}
