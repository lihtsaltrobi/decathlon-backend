package ee.industry.decathlon.service;

import ee.industry.decathlon.classifier.EventType;
import ee.industry.decathlon.dto.EventResultDTO;
import ee.industry.decathlon.model.EventResult;
import ee.industry.decathlon.repository.EventResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class EventResultServiceTests {

    private final String TEST_USER_NAME = "Test User";

    @InjectMocks
    private EventResultServiceImpl eventResultService;

    @Mock
    private EventResultRepository eventResultRepository;

    @Spy
    private PointServiceImpl pointService;

    @Before
    public void setUp() {
        Mockito.when(eventResultRepository.save(any()))
                .then(returnsFirstArg());
        Mockito.when(eventResultRepository.getCompetitors(any()))
                .thenAnswer(i -> new ArrayList<String>() {{ add(i.getArgument(0)); }});
        Mockito.when(eventResultRepository.findAll())
                .thenReturn(new ArrayList<>() {{
                    add(createEventResult());
                }});
    }

    @Test
    public void getAll_shouldPass() {
        List<EventResultDTO> results = eventResultService.getAll();
        assertEquals(results.size(), 1);
    }

    @Test
    public void getCompetitors_shouldPass() {
        List<String> competitorList = eventResultService.getCompetitors(TEST_USER_NAME);
        assertEquals(TEST_USER_NAME.toLowerCase(), competitorList.get(0));
    }

    @Test
    public void create_shouldPass() {
        EventResultDTO dto = eventResultService.create(createEventResultDTO());
        assertEquals((int)dto.getPoints(), 933);
    }

    private EventResult createEventResult() {
        return EventResult.builder()
                .competitor(TEST_USER_NAME)
                .eventType(EventType.HundredMeterRun)
                .score(10.68).build();
    }

    private EventResultDTO createEventResultDTO() {
        return EventResultDTO.builder()
                .eventType(EventType.HundredMeterRun)
                .competitor(TEST_USER_NAME)
                .score(10.68)
                .build();
    }
}
