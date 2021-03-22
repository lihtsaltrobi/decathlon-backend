package ee.industry.decathlon.service;

import ee.industry.decathlon.classifier.EventType;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PointServiceTests {

    private static final Logger LOG = LoggerFactory.getLogger(PointServiceTests.class);


    @InjectMocks
    private PointServiceImpl pointService;

    @Test
    public void calculateEventPoints_erkiNoolSydney() {
        Map<EventType, Pair<Double, Integer>> thousandPointBenchmarks = new HashMap<>() {{
            put(EventType.HundredMeterRun, new Pair<>(10.68, 933));
            put(EventType.LongJump, new Pair<>(776.0, 1000));
            put(EventType.ShotPut, new Pair<>(15.11, 796));
            put(EventType.HighJump, new Pair<>(200.0, 803));
            put(EventType.FourHundredMeterRun, new Pair<>(46.71, 973));
            put(EventType.Hurdles, new Pair<>(14.48, 913));
            put(EventType.Discus, new Pair<>(43.66, 739));
            put(EventType.PoleVault, new Pair<>(500.0, 910));
            put(EventType.JavelinThrow, new Pair<>(65.82, 826));
            put(EventType.FifteenHundredMeterRun, new Pair<>(269.48, 748));
        }};

        for (Map.Entry<EventType, Pair<Double, Integer>> benchmark : thousandPointBenchmarks.entrySet()) {
            int result = pointService.calculateEventPoints(benchmark.getKey(), benchmark.getValue().getValue0());
            LOG.info("Calculating " + benchmark.getKey());
            assertEquals(result, (int)benchmark.getValue().getValue1());
        }
    }

    @Test
    public void calculateEventPoints_lessThanZero() {
        int result = pointService.calculateEventPoints(EventType.HighJump, -2);
        assertEquals(result, 0);
    }
}
