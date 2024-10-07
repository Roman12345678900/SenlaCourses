package controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.controller.EquipmentController;
import senla.com.service.EquipmentService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class,
        loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "TRAINER")
    public void testFindById() throws Exception {
        Long id = 1L;

        Mockito.when(equipmentService.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/v1/equipment/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "TRAINER")
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/equipment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "TRAINER")
    public void testSave() throws Exception {
        mockMvc.perform(post("/api/v1/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Equipment\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "TRAINER")
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/equipment/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
