package ee.industry.decathlon.service;

import ee.industry.decathlon.classifier.EventType;
import ee.industry.decathlon.util.ScoreTable;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    /**
     * Calculates event score based on event type
     * Points = INT(A(B — P)C) for track events (faster time produces a higher score)
     * Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)
     * @param eventType
     * @param performance
     * @return
     */
    public int calculateEventPoints(EventType eventType, double performance) {

        if(performance < 0) {
            return 0;
        }

        ScoreTable.ScoreTableRow tableRow = ScoreTable.SCORE_TABLE.get(eventType);

        double eventScore = ScoreTable.isTrackEvent(eventType) ?
                tableRow.getB() - performance :
                performance - tableRow.getB();

        return (int)Math.floor(tableRow.getA() * Math.pow(eventScore, tableRow.getC()));
    }

}
