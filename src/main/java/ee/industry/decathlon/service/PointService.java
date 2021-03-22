package ee.industry.decathlon.service;

import ee.industry.decathlon.classifier.EventType;

public interface PointService {
    int calculateEventPoints(EventType eventType, double performance);
}
