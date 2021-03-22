package ee.industry.decathlon.repository;

import ee.industry.decathlon.classifier.EventType;
import ee.industry.decathlon.model.EventResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventResultRepositoryTests {

    @Autowired
    private EventResultRepository eventResultRepository;

    @Before
    public void setUp() {
        List<EventResult> scaffolding = Arrays.asList(
                EventResult.builder()
                        .eventType(EventType.HighJump)
                        .competitor("Erki Nool")
                        .score(10.68)
                        .points(933).build(),
                EventResult.builder()
                        .eventType(EventType.HighJump)
                        .competitor("Roman Šebrle")
                        .score(10.92)
                        .points(878).build()
        );
        eventResultRepository.saveAll(scaffolding);
    }

    @After
    public void tearDown() {
        eventResultRepository.deleteAll();
    }

    @Test
    public void getCompetitors_erki() {
        List<String> competitors = eventResultRepository.getCompetitors("erk");
        assertEquals(1, competitors.size());
        assertEquals("Erki Nool", competitors.get(0));
    }

    @Test
    public void getCompetitors_roman() {
        List<String> competitors = eventResultRepository.getCompetitors("šeb");
        assertEquals(1, competitors.size());
        assertEquals("Roman Šebrle", competitors.get(0));
    }

    @Test
    public void getCompetitors_empty() {
        List<String> competitors = eventResultRepository.getCompetitors("uib");
        assertEquals(0, competitors.size());
    }
}
