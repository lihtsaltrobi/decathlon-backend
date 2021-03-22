package ee.industry.decathlon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.industry.decathlon.classifier.EventType;
import ee.industry.decathlon.dto.EventResultDTO;
import ee.industry.decathlon.service.EventResultServiceImpl;
import ee.industry.decathlon.service.PointServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration
@WebAppConfiguration
public class EventResultControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventResultServiceImpl eventResultService;

    @MockBean
    private PointServiceImpl pointService;

    @Test
    public void saveResult_shouldPass() throws Exception {

        Mockito.when(eventResultService.create(any())).then(returnsFirstArg());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/").content(
                    json(EventResultDTO.builder().build())
                )
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void saveResult_created() throws Exception {

        Mockito.when(eventResultService.create(any())).then(returnsFirstArg());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/event").content(
                        json(EventResultDTO.builder().score(10).competitor("Test User").eventType(EventType.HighJump).build())
                ).contentType("application/json").with(csrf())
        ).andExpect(status().isOk());
    }

    private String json(Object input) {
        try {
            return new ObjectMapper().writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
