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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.controller.ReviewsController;
import senla.com.service.ReviewsService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class ReviewsControllerTest {

    @Mock
    private ReviewsService reviewsService;

    @InjectMocks
    private ReviewsController reviewsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewsController).build();
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "USER")
    public void testFindById() throws Exception {
        Long id = 1L;

        Mockito.when(reviewsService.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/v1/reviews/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "USER")
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "USER")
    public void testSave() throws Exception {
        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rating\":5, \"reviewText\":\"Excellent!\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "roman@gmail.com" , password = "1234" , roles = "USER")
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/reviews/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
