package ee.industry.decathlon.util;

import ee.industry.decathlon.classifier.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

public class ScoreTable {

    public static final EventType[] TRACK_EVENTS = new EventType[] {
            EventType.HundredMeterRun,
            EventType.FourHundredMeterRun,
            EventType.FifteenHundredMeterRun,
            EventType.Hurdles
    };

    public static boolean isTrackEvent(EventType eventType) {
        return Arrays.stream(TRACK_EVENTS).anyMatch(type -> eventType == type);
    }

    public static final HashMap<EventType, ScoreTableRow> SCORE_TABLE = new HashMap<>() {{
        put(EventType.HundredMeterRun, new ScoreTableRow(25.4347, 18, 1.81));
        put(EventType.LongJump, new ScoreTableRow(0.14354, 220, 1.4));
        put(EventType.ShotPut, new ScoreTableRow(51.39, 1.5, 1.05));
        put(EventType.HighJump, new ScoreTableRow(0.8465, 75, 1.42));
        put(EventType.FourHundredMeterRun, new ScoreTableRow(1.53775, 82, 1.81));
        put(EventType.Hurdles, new ScoreTableRow(5.74352, 28.5, 1.92));
        put(EventType.Discus, new ScoreTableRow(12.91, 4, 1.1));
        put(EventType.PoleVault, new ScoreTableRow(0.2797, 100, 1.35));
        put(EventType.JavelinThrow, new ScoreTableRow(10.14, 7, 1.08));
        put(EventType.FifteenHundredMeterRun, new ScoreTableRow(0.03768, 480, 1.85));
    }};

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ScoreTableRow {
        private double a;
        private double b;
        private double c;

    }

}
