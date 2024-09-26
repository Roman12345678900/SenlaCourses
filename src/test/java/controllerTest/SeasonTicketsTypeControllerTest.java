package controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.controller.SeasonTicketsTypeController;
import senla.com.service.SeasonTicketsTypeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class SeasonTicketsTypeControllerTest {

    @Mock
    private SeasonTicketsTypeService seasonTicketsTypeService;

    @InjectMocks
    private SeasonTicketsTypeController seasonTicketsTypeController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(seasonTicketsTypeController).build();
    }

    @Test
    public void TestFindById() throws Exception {
        Long id = 1L;

        Mockito.when(seasonTicketsTypeService.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/v1/season-tickets-types/{id}" ,id).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/season-tickets-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/api/v1/season-tickets-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/season-tickets-types/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
